package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.BookingDao;
import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.enums.BookingStatus;
import by.sergeev.hotel.entity.enums.RoomGrade;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class BookingDaoImpl extends AbstractDao<Booking> implements BookingDao {

    private static final Logger LOGGER = LogManager.getLogger(BookingDaoImpl.class);

    private static final String INSERTED_COLUMNS = "bookings.start_date, bookings.end_date, bookings.cost, " +
            "bookings.max_persons, bookings.number_of_beds, bookings.grade_id, bookings.has_Wifi, bookings.has_TV, " +
            "bookings.has_bathroom, bookings.user_id, bookings.number_of_rooms";
    private static final String CREATE_BOOKING = "INSERT INTO bookings (" + INSERTED_COLUMNS + ") VALUES " +
            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BOOKINGS_BY_USER = "SELECT id, start_date, end_date, cost, max_persons, number_of_beds," +
            " grade_id, has_Wifi, has_TV , has_bathroom, user_id, room_id, number_of_rooms, booking_status_id FROM bookings WHERE user_id = ?";
    private static final String FIND_BOOKING_BY_ID = "SELECT id, start_date, end_date, cost, max_persons, number_of_beds," +
            " grade_id, has_Wifi, has_TV , has_bathroom, user_id, room_id, number_of_rooms, booking_status_id FROM bookings WHERE id = ?";
    private static final String FIND_ALL_BOOKINGS = "SELECT id, start_date, end_date, cost, max_persons, number_of_beds," +
            " grade_id, has_Wifi, has_TV , has_bathroom, user_id, room_id, number_of_rooms, booking_status_id FROM bookings";
    private static final String UPDATE_BOOKING_STATUS_BY_ID = "UPDATE bookings SET booking_status_id = ? WHERE id = ?";
    private static final String ADD_ROOM_TO_BOOKING_BY_ID = "UPDATE bookings SET room_id = ?, cost = ?, booking_status_id = ? WHERE id = ?";
    private static final String DELETE_ROOM_FROM_BOOKING_BY_ID = "UPDATE bookings SET room_id = NULL , cost = 0.00 , booking_status_id = 0 WHERE id = ?";

    /**
     *
     * @param userId
     * @return
     * @throws DaoException
     */

    @Override
    public List<Booking> findBookingsByUserId(long userId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByPrStatement(connection, FIND_BOOKINGS_BY_USER,
                    ((preparedStatement, params) -> preparedStatement.setLong(1, userId)), userId);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem with bookingDao to find Bookings by userID", e);
        }
    }

    /**
     *
     * @param bookingId
     * @return
     * @throws DaoException
     */
    @Override
    public Optional<Booking> findBookingById(long bookingId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return Optional.ofNullable(tryFindEntityByPrStatement(connection, FIND_BOOKING_BY_ID,
                    ((preparedStatement, params) -> preparedStatement.setLong(1, bookingId)), bookingId));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem with bookingDao to find Bookings by userID", e);
        }
    }

    /**
     * @param bookingId
     * @param statusId
     * @throws DaoException
     */
    @Override
    public void changeBookingStatusById(long bookingId, int statusId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(UPDATE_BOOKING_STATUS_BY_ID)) {
                preparedSt.setInt(1, statusId);
                preparedSt.setLong(2, bookingId);
                if (preparedSt.getUpdateCount() != 1) {
                    throw new DaoException("Status was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with change book status", e);
        }
    }

    /**
     *
     * @param freshBooking
     * @throws DaoException
     */
    @Override
    public void createBooking(Booking freshBooking) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_BOOKING)) {
                preparedSt.setString(1, freshBooking.getStartDate());
                preparedSt.setString(2, freshBooking.getEndDate());
                preparedSt.setBigDecimal(3, freshBooking.getCost());
                preparedSt.setInt(4, freshBooking.getMaxPersons());
                preparedSt.setInt(5, freshBooking.getNumberOfBeds());
                preparedSt.setInt(6, freshBooking.getRoomGrade().ordinal());
                preparedSt.setBoolean(7, freshBooking.isHasWifi());
                preparedSt.setBoolean(8, freshBooking.isHasTV());
                preparedSt.setBoolean(9, freshBooking.isHasBathroom());
                preparedSt.setLong(10, freshBooking.getUserId());
                preparedSt.setInt(11, freshBooking.getNumberOfRooms());
                if (preparedSt.executeUpdate() != 1) {
                    throw new DaoException("Status was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with create booking", e);
        }
    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    @Override
    protected Booking makeEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getInt(1);
        String startDate = resultSet.getString(2);
        String endDate = resultSet.getString(3);
        BigDecimal cost = resultSet.getBigDecimal(4);
        int maxPersons = resultSet.getInt(5);
        int numberOfBeds = resultSet.getInt(6);
        int gradeId = resultSet.getInt(7);
        boolean hasWifi = resultSet.getBoolean(8);
        boolean hasTV = resultSet.getBoolean(9);
        boolean hasBathroom = resultSet.getBoolean(10);
        int userId = resultSet.getInt(11);
        int roomId = Optional.ofNullable(resultSet.getInt(12)).orElse(-1);
        int numberOfRooms = resultSet.getInt(13);
        int statusId = resultSet.getInt(14);
        Room room = null;
        if (roomId > 0) {
            RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();
            try {
                room = roomDao.findRoomById(roomId).get();
            } catch (DaoException e) {
                LOGGER.info("Can't find room by this id in bookingDao");
            }
        }
        BookingStatus status = BookingStatus.values()[statusId];
        RoomGrade roomGrade = RoomGrade.values()[gradeId];
        return new Booking(id, startDate, endDate, cost, maxPersons, numberOfBeds, roomGrade, hasWifi, hasTV, hasBathroom,
                userId, room, status, numberOfRooms);
    }

    @Override
    public void addRoomToBooking(long bookingsId, long roomId, BigDecimal cost) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(ADD_ROOM_TO_BOOKING_BY_ID)) {
                preparedSt.setLong(1, roomId);
                preparedSt.setBigDecimal(2, cost);
                preparedSt.setInt(3, BookingStatus.WAITING_FOR_PAYMENT.ordinal());
                preparedSt.setLong(4, bookingsId);
                if (preparedSt.executeUpdate() != 1) {
                    throw new DaoException("Booking was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with add room to booking", e);
        }
    }

    @Override
    public boolean deleteRoomFromBooking(long bookingId) throws DaoException {
        boolean isCommandSuccess;
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(DELETE_ROOM_FROM_BOOKING_BY_ID)) {
                preparedSt.setLong(1, bookingId);
                isCommandSuccess = preparedSt.executeUpdate() == 1;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with add room to booking", e);
        }
        return isCommandSuccess;
    }

    @Override
    public List<Booking> findAll() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByQuery(connection, FIND_ALL_BOOKINGS);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem in RoomDao, while trying to find all rooms", e);
        }
    }

    @Override
    public boolean changeBookingStatusForPayment(ProxyConnection proxyConnection, long bookingId) throws DaoException {
        boolean isChanged = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_BOOKING_STATUS_BY_ID)) {
            preparedStatement.setInt(1, 3);
            preparedStatement.setLong(2, bookingId);
            isChanged = (preparedStatement.executeUpdate() == 1);
            if (preparedStatement.getUpdateCount() != 1) {
                LOGGER.fatal("Problem with updating booking");
                throw new DaoException("Booking status was not updated");
            }
        } catch (SQLException e) {
            LOGGER.fatal("Problem with updating booking in database connection");
            throw new DaoException("Problem with updating booking", e);
        }
        return isChanged;
    }
}

