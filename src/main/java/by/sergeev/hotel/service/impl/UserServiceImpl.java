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
import by.sergeev.hotel.utils.BCryptHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();


    public boolean checkIsEmailFree(String email) throws ServiceException {
        try {
            return tryCheckIsLoginFree(email);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying to check is login free", e);
            throw new ServiceException(e);
        } catch (DaoException e) {
            LOGGER.error("Problem with UserDAO, while trying to check is login free", e);
            throw new ServiceException(e);
        }

    }

    private boolean tryCheckIsLoginFree(String email) throws ConnectionPoolException, DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            User user = userDao.findUserByEmail(email, connection);
            return user == null;
        }
    }
//TODO all Exceptions in one
    @Override
    public void register(String email, String password, String firstName, String lastName) throws ServiceException {
        try {
            tryRegister(email, password, firstName, lastName);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection, while trying registration", e);
            throw new ServiceException(e);
        } catch (DaoException e) {
            LOGGER.error("Problem with UserDAO, while trying registration", e);
            throw new ServiceException(e);
        }
    }

    private void tryRegister(String email, String password, String firstName, String lastName) throws ConnectionPoolException, DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            String passwordHashed = BCryptHash.hashPassword(password);
            User user = new User(email, passwordHashed, firstName, lastName, Role.getRole(0));
            userDao.create(user, connection);
        }
    }

    @Override
    public User logIn(String email, String password) throws ServiceException {
        try {
            return tryLogIn(email, password);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Problem with getting connection", e);
            throw new ServiceException(e);
        } catch (DaoException e) {
            LOGGER.error("Problem with UserDAO", e);
            throw new ServiceException(e);
        }
    }

    private User tryLogIn(String email, String password) throws DaoException, ConnectionPoolException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            UserDao userDAO = DaoFactory.getInstance().getUserDao();
            User user = userDAO.findUserByEmail(email, connection);
            if (user != null && BCryptHash.checkPassword(password, user.getPassword())) {
                return user;
            }
            return null;
        }
    }
}

