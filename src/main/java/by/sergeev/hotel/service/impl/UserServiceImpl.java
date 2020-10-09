package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.Role;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import by.sergeev.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();


    public boolean checkIsLoginFree(String login) throws ServiceException {
        try {
            return tryCheckIsLoginFree(login);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying to check is login free", e);
            throw new ServiceException(e);
        } catch (DaoException e) {
            LOGGER.error("Problem with UserDAO, while trying to check is login free", e);
            throw new ServiceException(e);
        }

    }

    private boolean tryCheckIsLoginFree(String login) throws ConnectionPoolException, DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            User user = userDao.findUserByLogin(login, connection);
            return user == null;
        }
    }

    @Override
    public void register(String login, String password, String email, String firstName, String lastName) throws ServiceException {
        try {
            tryRegister(login,password,email,firstName,lastName);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying registration", e);
            throw new ServiceException(e);
        } catch (DaoException e) {
            LOGGER.error("Problem with UserDAO, while trying registration", e);
            throw new ServiceException(e);
        }
    }

    private void tryRegister(String login, String password, String email, String firstName, String lastName) throws ConnectionPoolException, DaoException{
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
         //   User user = new User(login,password,email,firstName,lastName, Role.getRole(0));
         //   userDao.create(user, connection);
        }
    }
}
