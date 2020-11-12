package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll() throws ServiceException;

    boolean checkIsValid(String email, String password, String firstName, String lastName) throws ServiceException;

    Optional<User> logIn(String email, String password) throws ServiceException;

    void register(String email, String password, String firstName, String lastName) throws ServiceException;

    void addBalance(int userId, BigDecimal amount) throws ServiceException;

    Optional<User> findUserById(int userId) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    boolean updateUserPassword(int userId, String oldPassword, String newPassword) throws ServiceException;

    boolean deleteAccount(int userId, String password) throws ServiceException;
}
