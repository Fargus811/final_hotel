package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.ServiceException;

public interface UserService {

    boolean checkIsEmailFree(String login) throws ServiceException;

    User logIn(String email, String password) throws ServiceException;

    void register(String email, String password, String firstName, String lastName) throws ServiceException;
}
