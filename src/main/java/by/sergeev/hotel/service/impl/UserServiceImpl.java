package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.Role;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.util.BCryptHash;
import by.sergeev.hotel.validator.RequestValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao = DaoFactory.daoFactory.getUserDao();

    @Override
    public List<User> findAll() throws ServiceException {
        return userDao.findAll();
    }

    @Override
    public boolean checkIsValid(String email, String password, String firstName, String lastName) throws ServiceException {
        boolean isValid = false;
        //TODO validator
        if (RequestValidator.isValidEmail(email) && RequestValidator.isValidPassword(password) &&
                RequestValidator.isValidFirstName(firstName) && RequestValidator.isValidLastName(lastName)) {
            try {
                isValid = checkIsEmailFree(email);
            } catch (DaoException e) {
                LOGGER.error("Problem with UserDAO, while trying to check is login free", e);
                throw new ServiceException(e);
            }
        }
        return isValid;
    }

    private boolean checkIsEmailFree(String email) throws DaoException {
        return Objects.isNull(userDao.findUserByEmail(email));
    }

    @Override
    public void register(String email, String password, String firstName, String lastName) {
        String passwordHashed = BCryptHash.hashPassword(password);
        User user = new User(email, firstName, lastName, Role.getRole(0));
        userDao.create(user, passwordHashed);
    }

    @Override
    public Optional<User> logIn(String email, String password) throws ServiceException {
        try {
            Optional<User> userOptional = userDao.findUserByEmail(email);
            if (userOptional.isPresent()) {
                String hashPassword = userDao.findPasswordById(userOptional.get().getId());
                if (BCryptHash.checkPassword(password, hashPassword)) {
                    return userOptional;
                } else {
                    return Optional.empty();
                }
            } else {
                return userOptional;
            }
        } catch (DaoException e) {
            LOGGER.error("Problem with UserDAO", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void addBalance(int userId, double balance, String password) throws ServiceException {
        try {
            User user = userDao.findEntityById(userId).get();
            user.setBalance(user.getBalance() + balance);
            if (BCryptHash.checkPassword(password, userDao.findPasswordById(userId)))
                userDao.updateEntity(user, password);
        } catch (DaoException e) {
            throw new ServiceException("Problem with adding balance in user service", e);
        }
    }

    @Override
    public Optional<User> findUserById(int userId) {
        return userDao.findEntityById(userId);
    }
}

