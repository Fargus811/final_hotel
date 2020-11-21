package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAllUsers() throws DaoException;

    Optional<User> findEntityById(long id) throws DaoException;

    void create(User user, String password) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    String findPasswordById(long userId) throws DaoException;

    void updateUserInformation(User user) throws DaoException;

    void updateUserPassword(long userId, String hashPassword) throws DaoException ;

    void updateUserBalance(long userId, BigDecimal balance) throws DaoException;

    void changeAccountStatus(long userId, int statusId) throws DaoException;

    boolean payForBooking(ProxyConnection proxyConnection, long userId, BigDecimal cost) throws DaoException;
}
