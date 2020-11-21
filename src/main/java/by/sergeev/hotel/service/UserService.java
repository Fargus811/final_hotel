package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    boolean checkIsValid(String email, String password, String firstName, String lastName) throws ServiceException;

    boolean checkIsEmailFree(String email) throws ServiceException;

    Optional<User> logIn(String email, String password) throws ServiceException;

    void register(String email, String password, String firstName, String lastName) throws ServiceException;

    void addBalance(long userId, BigDecimal amount) throws ServiceException;

    Optional<User> findUserById(long userId) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    boolean updateUserPassword(long userId, String oldPassword, String newPassword) throws ServiceException;

    boolean deleteAccount(long userId, String password) throws ServiceException;

    void changeAccountStatus(long userId, int statusId) throws ServiceException;

}
