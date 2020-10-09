package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.Role;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;
import by.sergeev.hotel.utils.EntityAttribute;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String SELECTED_COLUMNS = "user_id, login, password, email, first_name, last_name, balance," +
            "role_id";
    private static final String FIND_USER_BY_LOGIN_SQL = "SELECT " + SELECTED_COLUMNS + " FROM user WHERE login = ?";


    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    @Override
    public User findEntityById(int id) throws DaoException {
        return null;
    }

    @Override
    public void create(User entity, ProxyConnection connection) throws DaoException {

    }

    @Override
    public void update(User user) throws DaoException {

    }

    @Override
    protected User makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(EntityAttribute.FIRST_ATTRIBUTE);
        String login = rs.getString(EntityAttribute.SECOND_ATTRIBUTE);
        String password = rs.getString(EntityAttribute.THRIRD_ATTRIBUTE);
        String email = rs.getString(EntityAttribute.FOUTH_ATTRIBUTE);
        String firstName = rs.getString(EntityAttribute.FIFTH_ATTRIBUTE);
        String lastName = rs.getString(EntityAttribute.SIXTH_ATTRIBUTE);
        Double balance = rs.getDouble(EntityAttribute.SEVENTH_ATTRIBUTE);
        Role role =Role.getRole(rs.getInt(EntityAttribute.EIGHTH_ATTRIBUTE));
        return new User(id,login,password,email,firstName,lastName,balance,role);
    }

    @Override
    public User findUserByLogin(String login, ProxyConnection proxyConnection) throws DaoException {
        try {
            return tryFindEntityByPrStatement(proxyConnection,FIND_USER_BY_LOGIN_SQL);
        } catch (SQLException e) {
            throw new DaoException("Problem when trying to find user by login", e);
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
