package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.AccountStatus;
import by.sergeev.hotel.entity.enums.Role;
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

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private static final String SELECTED_COLUMNS = "id, email, first_name, last_name, balance," +
            "role_id, account_status_id";
    private static final String FIND_ALL_USERS = "SELECT " + SELECTED_COLUMNS + " FROM users WHERE role_id = 0";
    private static final String INSERTED_COLUMNS = "email, password, first_name, last_name, role_id, account_status_id";
    private static final String FIND_USER_BY_EMAIL = "SELECT " + SELECTED_COLUMNS + " FROM users WHERE email = ?";
    private static final String FIND_USER_BY_ID = "SELECT " + SELECTED_COLUMNS + " FROM users WHERE id = ?";
    private static final String FIND_USER_PASSWORD_BY_ID = "SELECT password FROM users WHERE id = ?";
    private static final String CREATE_USER = "INSERT INTO users (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CHECK_USER_BALANCE = "SELECT balance FROM users WHERE id = ?";
    private static final String UPDATE_USER_INFO = "UPDATE users SET email = ?, first_name = ?, last_name = ? WHERE id = ?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";
    private static final String UPDATE_USER_BALANCE = "UPDATE users SET balance = ? WHERE id = ?";
    private static final String UPDATE_USER_ACCOUNT_STATUS = "UPDATE users SET account_status_id = ? WHERE id = ?";

    @Override
    public List<User> findAllUsers() throws DaoException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByQuery(proxyConnection, FIND_ALL_USERS);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem in UserDao, while trying to fina all users", e);
        }
    }

    @Override
    public Optional<User> findEntityById(long id) throws DaoException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection()) {
            return Optional.ofNullable(tryFindEntityByPrStatement(proxyConnection, FIND_USER_BY_ID,
                    ((preparedStatement, params) -> preparedStatement.setLong(1, id)), id));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem when trying to find user by email", e);
        }
    }

    @Override
    public void create(User entity, String password) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_USER)) {
                preparedSt.setString(1, entity.getEmail());
                preparedSt.setString(2, password);
                preparedSt.setString(3, entity.getFirstName());
                preparedSt.setString(4, entity.getLastName());
                preparedSt.setInt(5, entity.getRole().ordinal());
                preparedSt.setInt(6, entity.getAccountStatus().ordinal());
                preparedSt.executeUpdate();
                if (preparedSt.getUpdateCount() != 1) {
                    throw new DaoException("User was not created");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected User makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String email = rs.getString(2);
        String firstName = rs.getString(3);
        String lastName = rs.getString(4);
        BigDecimal balance = rs.getBigDecimal(5);
        Role role = Role.values()[(rs.getInt(6))];
        AccountStatus accountStatus = AccountStatus.values()[rs.getInt(7)];
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBalance(balance);
        user.setRole(role);
        user.setAccountStatus(accountStatus);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return Optional.ofNullable(tryFindEntityByPrStatement(connection, FIND_USER_BY_EMAIL,
                    ((preparedStatement, params) -> preparedStatement.setString(1, email)), email));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem when trying to find user by email", e);
        }
    }

    @Override
    public String findPasswordById(long userId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_PASSWORD_BY_ID)) {
                preparedStatement.setLong(1, userId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                String password = rs.getString(1);
                return password;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem with find password by user id", e);
        }
    }

    @Override
    public void updateUserInformation(User user) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_INFO)) {
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.setString(3, user.getLastName());
                preparedStatement.setLong(4, user.getId());
                preparedStatement.executeUpdate();
                if (preparedStatement.getUpdateCount() != 1) {
                    throw new DaoException("User was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with updating user", e);
        }
    }

    public void updateUser(User entity, String password) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_BALANCE)) {
                preparedStatement.setLong(1, entity.getId());
                preparedStatement.setString(2, entity.getEmail());
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, entity.getFirstName());
                preparedStatement.setString(5, entity.getLastName());
                preparedStatement.setBigDecimal(6, entity.getBalance());
                preparedStatement.executeUpdate();
                if (preparedStatement.getUpdateCount() != 1) {
                    throw new DaoException("User was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with updating user", e);
        }
    }

    @Override
    public void updateUserPassword(long userId, String hashPassword) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
                preparedStatement.setString(1, hashPassword);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();
                if (preparedStatement.getUpdateCount() != 1) {
                    throw new DaoException("User was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with updating user", e);
        }
    }

    @Override
    public void updateUserBalance(long userId, BigDecimal balance) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BALANCE)) {
                preparedStatement.setBigDecimal(1, balance);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();
                if (preparedStatement.getUpdateCount() != 1) {
                    LOGGER.fatal("Problem with updating user");
                    throw new DaoException("User balance was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.fatal("Problem with updating user in database connection");
            throw new DaoException("Problem with updating user", e);
        }
    }

    @Override
    public void changeAccountStatus(long userId, int statusId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ACCOUNT_STATUS)) {
                preparedStatement.setInt(1, statusId);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();
                if (preparedStatement.getUpdateCount() != 1) {
                    LOGGER.fatal("Problem with updating user");
                    throw new DaoException("User status was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.fatal("Problem with updating user in database connection");
            throw new DaoException("Problem with updating user", e);
        }
    }

    @Override
    public boolean payForBooking(ProxyConnection proxyConnection, long userId, BigDecimal totalBalance) throws DaoException {
        boolean isPaid = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_USER_BALANCE)) {
            preparedStatement.setBigDecimal(1, totalBalance);
            preparedStatement.setLong(2, userId);
            isPaid = (preparedStatement.executeUpdate() == 1);
            if (preparedStatement.getUpdateCount() != 1) {
                LOGGER.fatal("Problem with updating user");
                throw new DaoException("User balance was not updated");
            }
        } catch (SQLException e) {
            LOGGER.fatal("Problem with updating user in database connection");
            throw new DaoException("Problem with updating user", e);
        }
        return isPaid;
    }
}
