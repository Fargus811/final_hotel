package by.sergeev.hotel.service;

import by.sergeev.hotel.exception.ServiceException;

public interface UserService {

    boolean checkIsLoginFree(String login) throws ServiceException;

    void register(String login, String password, String email, String firstName, String lastName) throws ServiceException;
}
