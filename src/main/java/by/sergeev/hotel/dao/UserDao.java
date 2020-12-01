package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface UserDao {

    /**
     * Find all users list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Find entity by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findEntityById(long id) throws DaoException;

    /**
     * Create.
     *
     * @param user     the user
     * @param password the password
     * @throws DaoException the dao exception
     */
    void create(User user, String password) throws DaoException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Find password by id string.
     *
     * @param userId the user id
     * @return the string
     * @throws DaoException the dao exception
     */
    String findPasswordById(long userId) throws DaoException;

    /**
     * Update user information.
     *
     * @param user the user
     * @throws DaoException the dao exception
     */
    void updateUserInformation(User user) throws DaoException;

    /**
     * Update user password.
     *
     * @param userId       the user id
     * @param hashPassword the hash password
     * @throws DaoException the dao exception
     */
    void updateUserPassword(long userId, String hashPassword) throws DaoException ;

    /**
     * Update user balance.
     *
     * @param userId  the user id
     * @param balance the balance
     * @throws DaoException the dao exception
     */
    void updateUserBalance(long userId, BigDecimal balance) throws DaoException;

    /**
     * Change account status.
     *
     * @param userId   the user id
     * @param statusId the status id
     * @throws DaoException the dao exception
     */
    void changeAccountStatus(long userId, int statusId) throws DaoException;

    /**
     * Pay for booking transaction boolean.
     *
     * @param proxyConnection the proxy connection
     * @param userId          the user id
     * @param cost            the cost
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean payForBookingTransaction(ProxyConnection proxyConnection, long userId, BigDecimal cost) throws DaoException;
}
