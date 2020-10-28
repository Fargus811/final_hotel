package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.Role;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import by.sergeev.hotel.util.EntityAttribute;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {


    private static final String SELECTED_COLUMNS = "id, email, first_name, last_name, balance," +
            "role_id";
    private static final String FIND_ALL_USERS = "SELECT " + SELECTED_COLUMNS + " FROM users";
    private static final String INSERTED_COLUMNS = "email, password, first_name, last_name, role_id";
    private static final String FIND_USER_BY_EMAIL = "SELECT " + SELECTED_COLUMNS + " FROM users WHERE email = ?";
    private static final String FIND_USER_BY_ID = "SELECT " + SELECTED_COLUMNS + " FROM users WHERE id = ?";
    private static final String FIND_USER_PASSWORD_BY_ID = "SELECT password FROM users WHERE id = ?";
    private static final String CREATE_USER = "INSERT INTO users (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?, ?)";
    private static final String CHECK_USER_BALANCE = "SELECT balance FROM users WHERE id = ?";
    private static final int FIRST_PARAMETER_INDEX = 1;

    @Override
    public List<User> findAll() throws DaoException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByQuery(proxyConnection, FIND_ALL_USERS);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem in UserDao, while trying to fina all users", e);
        }
    }

    @Override
    public Optional<User> findEntityById(int id) throws DaoException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection()) {
            return Optional.ofNullable(tryFindEntityByPrStatement(proxyConnection, FIND_USER_BY_ID,
                    ((preparedStatement, params) -> preparedStatement.setInt(FIRST_PARAMETER_INDEX, id)), id));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem when trying to find user by email", e);
        }
    }

    @Override
    public void create(User entity, String password) {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_USER)) {
                preparedSt.setString(1, entity.getEmail());
                preparedSt.setString(2, password);
                preparedSt.setString(3, entity.getFirstName());
                preparedSt.setString(4, entity.getLastName());
                preparedSt.setInt(5, entity.getRole().getId());
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
        int id = rs.getInt(EntityAttribute.FIRST_ATTRIBUTE);
        String email = rs.getString(EntityAttribute.SECOND_ATTRIBUTE);
        String firstName = rs.getString(EntityAttribute.THIRD_ATTRIBUTE);
        String lastName = rs.getString(EntityAttribute.FOURTH_ATTRIBUTE);
        double balance = rs.getDouble(EntityAttribute.FIFTH_ATTRIBUTE);
        Role role = Role.getRole(rs.getInt(EntityAttribute.SIXTH_ATTRIBUTE));
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBalance(balance);
        user.setRole(role);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return  Optional.ofNullable(tryFindEntityByPrStatement(connection, FIND_USER_BY_EMAIL,
                    ((preparedStatement, params) -> preparedStatement.setString(FIRST_PARAMETER_INDEX, email)), email));
        } catch (SQLException | DaoException | ConnectionPoolException e) {
            throw new DaoException("Problem when trying to find user by email", e);
        }
    }

    @Override
    public String findPasswordById(int userId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_PASSWORD_BY_ID)) {
                preparedStatement.setInt(EntityAttribute.FIRST_ATTRIBUTE, userId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                String password = rs.getString(1);
                return password;
            } catch (SQLException e) {
                throw new DaoException("Problem with find password by user id", e);
            }
        }
    }

    @Override
    public void updateEntity(User entity, String password) {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_BALANCE)) {
                preparedStatement.setInt(EntityAttribute.FIRST_ATTRIBUTE, entity.getId());
                preparedStatement.setString(EntityAttribute.SECOND_ATTRIBUTE, entity.getEmail());
                preparedStatement.setString(EntityAttribute.THIRD_ATTRIBUTE, password);
                preparedStatement.setString(EntityAttribute.FOURTH_ATTRIBUTE, entity.getFirstName());
                preparedStatement.setString(EntityAttribute.FIFTH_ATTRIBUTE, entity.getLastName());
                preparedStatement.setDouble(EntityAttribute.SIXTH_ATTRIBUTE, entity.getBalance());
                preparedStatement.executeUpdate();
                if (preparedStatement.getUpdateCount() != 1) {
                    throw new DaoException("User was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with updating user", e);
        }
    }
}
