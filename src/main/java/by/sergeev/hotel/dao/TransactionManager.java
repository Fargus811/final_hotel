package by.sergeev.hotel.dao;

import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * The {@code TransactionManager} class represents transaction manager.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class TransactionManager {

    private static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);
    private static final TransactionManager instance = new TransactionManager();
    private final UserDao userDao = DaoFactory.daoFactory.getUserDao();
    private final BookingDao bookingDao = DaoFactory.daoFactory.getBookingDao();

    private TransactionManager() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TransactionManager getInstance() {
        return instance;
    }

    /**
     * Add image and tattoo transaction.
     *
     * @param userId    the user id
     * @param bookingId the booking id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean payForBooking(BigDecimal totalBalance, long userId, long bookingId) throws DaoException {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = ConnectionPool.getInstance().takeConnection();
            proxyConnection.setAutoCommit(false);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with taking proxyConnection", e);
        }
        boolean isBookingPaid = userDao.payForBooking(proxyConnection, userId, totalBalance);
        boolean isStatusChanged = false;
        if (isBookingPaid) {
            isStatusChanged = bookingDao.changeBookingStatusForPayment(proxyConnection, bookingId);
        }
        try {
            proxyConnection.commit();
        } catch (SQLException ex) {
            rollbackConnection(proxyConnection);
            throw new DaoException("Error while pay for booking ", ex);
        } finally {
            closeConnection(proxyConnection);
        }
        return isStatusChanged;
    }

    private void rollbackConnection(ProxyConnection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while rollback transaction: {}", connection, e);
        }
    }

    private void closeConnection(ProxyConnection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error while closing connection: {}", connection, e);
            }
        }
    }
}
