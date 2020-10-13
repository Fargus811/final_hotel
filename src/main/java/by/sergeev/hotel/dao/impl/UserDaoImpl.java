package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.Role;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;
import by.sergeev.hotel.utils.EntityAttribute;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String SELECTED_COLUMNS = "id, email, password, first_name, last_name, balance," +
            "role_id";
    private static final String INSERTED_COLUMNS = "email, password, first_name, last_name, role_id";
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT " + SELECTED_COLUMNS + " FROM users WHERE email = ?";
    private static final String CREATE_USER = "INSERT INTO users (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?, ?)";
    private static final int FIRST_PARAMETER_INDEX = 1;

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    @Override
    public User findEntityById(int id) throws DaoException {
        return null;
    }

    @Override
    public void create(User entity, ProxyConnection proxyConnection) throws DaoException {
        try {
            tryCreate(entity,proxyConnection);
        } catch (SQLException e) {
            throw new DaoException("Problem when trying to create user", e);
        }
    }

    private void tryCreate(User entity, ProxyConnection proxyConnection) throws SQLException, DaoException {
        try (PreparedStatement preparedSt = proxyConnection.prepareStatement(CREATE_USER)) {
            preparedSt.setString(1, entity.getEmail());
            preparedSt.setString(2, entity.getPassword());
            preparedSt.setString(3, entity.getFirst_name());
            preparedSt.setString(4, entity.getLast_name());
            preparedSt.setInt(5, entity.getRole().getId());
            preparedSt.executeUpdate();
            if (preparedSt.getUpdateCount() != 1) {
                throw new DaoException("User was not created");
            }
        }
    }

    @Override
    protected User makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(EntityAttribute.FIRST_ATTRIBUTE);
        String password = rs.getString(EntityAttribute.SECOND_ATTRIBUTE);
        String email = rs.getString(EntityAttribute.THRIRD_ATTRIBUTE);
        String firstName = rs.getString(EntityAttribute.FOUTH_ATTRIBUTE);
        String lastName = rs.getString(EntityAttribute.FIFTH_ATTRIBUTE);
        Double balance = rs.getDouble(EntityAttribute.SIXTH_ATTRIBUTE);
        Role role =Role.getRole(rs.getInt(EntityAttribute.SEVENTH_ATTRIBUTE));
        return new User(id,email,password,firstName,lastName,balance,role);
    }

    @Override
    public User findUserByEmail(String email, ProxyConnection proxyConnection) throws DaoException {
        try {
            return tryFindEntityByPrStatement(proxyConnection, FIND_USER_BY_EMAIL_SQL,
                    ((preparedStatement, params) -> preparedStatement.setString(FIRST_PARAMETER_INDEX,email)),email);
        } catch (SQLException e) {
            throw new DaoException("Problem when trying to find user by email", e);
        }
    }

    @Override
    public void updatePassword(String login, String password) throws DaoException {

    }

    @Override
    public void authorization(String login, String password) throws DaoException {

    }

    @Override
    public void updateEmail(String login, String email) throws DaoException {

    }
}
