package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


/**
 * The interface User service.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface UserService {

    /**
     * Find all users list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Check is valid boolean.
     *
     * @param email     the email
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @return the boolean
     */
    boolean checkIsValid(String email, String password, String firstName, String lastName);

    /**
     * Check is email free boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkIsEmailFree(String email) throws ServiceException;

    /**
     * Log in optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> logIn(String email, String password) throws ServiceException;

    /**
     * Register.
     *
     * @param email     the email
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @throws ServiceException the service exception
     */
    void register(String email, String password, String firstName, String lastName) throws ServiceException;

    /**
     * Add balance.
     *
     * @param userId the user id
     * @param amount the amount
     * @throws ServiceException the service exception
     */
    void addBalance(long userId, BigDecimal amount) throws ServiceException;

    /**
     * Find user by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserById(long userId) throws ServiceException;

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUser(User user) throws ServiceException;

    /**
     * Update user password boolean.
     *
     * @param userId      the user id
     * @param oldPassword the old password
     * @param newPassword the new password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserPassword(long userId, String oldPassword, String newPassword) throws ServiceException;

    /**
     * Delete account boolean.
     *
     * @param userId   the user id
     * @param statusId the status id
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteAccount(long userId, int statusId, String password) throws ServiceException;

    /**
     * Change account status.
     *
     * @param userId   the user id
     * @param statusId the status id
     * @throws ServiceException the service exception
     */
    void changeAccountStatus(long userId, int statusId) throws ServiceException;

    /**
     * Activate account boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean activateAccount(String email) throws ServiceException;
}
