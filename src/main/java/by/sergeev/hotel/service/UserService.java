package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    boolean checkIsValid(String email, String password, String firstName, String lastName) throws ServiceException;

    Optional<User> logIn(String email, String password) throws ServiceException;

    void register(String email, String password, String firstName, String lastName);

    void addBalance(int userId, double balance, String password) throws ServiceException;

    Optional<User> findUserById(int userId);
}
