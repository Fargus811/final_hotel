package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.AccountStatus;
import by.sergeev.hotel.entity.enums.Role;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.util.BCryptHash;
import by.sergeev.hotel.validator.UserFormValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao = DaoFactory.daoFactory.getUserDao();

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAll in roomDao", e);
        }
    }

    @Override
    public boolean checkIsValid(String email, String password, String firstName, String lastName) throws ServiceException {
        boolean isValid = false;
        if (UserFormValidator.isValidEmail(email) && UserFormValidator.isValidPassword(password) &&
                UserFormValidator.isValidFirstName(firstName) && UserFormValidator.isValidLastName(lastName)) {
            try {
                isValid = checkIsEmailFree(email);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isValid;
    }

    private boolean checkIsEmailFree(String email) throws DaoException {
        return !(userDao.findUserByEmail(email).isPresent());
    }

    @Override
    public void register(String email, String password, String firstName, String lastName) throws ServiceException {
        String passwordHashed = BCryptHash.hashPassword(password);
        User user = new User(email, firstName, lastName, Role.USER, AccountStatus.ACTIVE);
        try {
            userDao.create(user, passwordHashed);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method create in userDao", e);
        }
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
            throw new ServiceException(e);
        }
    }

    @Override
    public void addBalance(int userId, BigDecimal amount) throws ServiceException {
        try {
            Optional<User> userOptional = userDao.findEntityById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                BigDecimal currentBalance = user.getBalance();
                BigDecimal newBalance = currentBalance.add(amount);
                userDao.updateUserBalance(userId, newBalance);
            }
        } catch (DaoException e) {
            throw new ServiceException("Problem with adding balance in user service", e);
        }
    }

    @Override
    public Optional<User> findUserById(int userId) throws ServiceException {
        try {
            return userDao.findEntityById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findUserById in userDao", e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean isUpdated = false;
        if (UserFormValidator.isValidEmail(user.getEmail()) && UserFormValidator.isValidFirstName(user.getFirstName())
                && UserFormValidator.isValidLastName(user.getLastName())) {
            try {
                userDao.updateUserInformation(user);
                isUpdated = true;
            } catch (DaoException e) {
                throw new ServiceException("Problem in method updateUserInformation in userDao", e);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserPassword(int userId, String oldPassword, String newPassword) throws ServiceException {
        boolean isUpdated = false;
        String userPassword = getUserPassword(userId);
        if (!oldPassword.equals(newPassword) && UserFormValidator.isValidPassword(oldPassword) && UserFormValidator.isValidPassword(newPassword)
                && BCryptHash.checkPassword(oldPassword, userPassword)) {
            try {
                userDao.updateUserPassword(userId, BCryptHash.hashPassword(newPassword));
            } catch (DaoException e) {
                throw new ServiceException("Problem with updating password in user dao", e);
            }
            isUpdated = true;
        }
        return isUpdated;
    }

    private String getUserPassword(int userId) throws ServiceException {
        String userPassword;
        try {
            userPassword = userDao.findPasswordById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Problem with find user password in user dao", e);
        }
        return userPassword;
    }

    @Override
    public boolean deleteAccount(int userId, String password) throws ServiceException {
        boolean isDeleted = false;
        String userPassword = getUserPassword(userId);
        if (BCryptHash.checkPassword(password, userPassword)) {
            try {
                userDao.deleteAccount(userId);
            } catch (DaoException e) {
                throw new ServiceException("Problem with method delete account in user dao", e);
            }
            isDeleted = true;
        }

        return isDeleted;
    }
}

