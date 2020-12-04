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
import java.util.Optional;

/**
 * The type User service.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static final int ACTIVE_STATUS_ACCOUNT = 0;

    private DaoFactory daoFactory;
    private UserDao userDao;

    public UserServiceImpl() {
       daoFactory = DaoFactory.daoFactory;
       userDao = daoFactory.getUserDao();
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAllUsers in roomDao", e);
        }
    }

    @Override
    public boolean checkIsValid(String email, String password, String firstName, String lastName) {
        return UserFormValidator.isValidEmail(email) && UserFormValidator.isValidPassword(password) &&
                UserFormValidator.isValidFirstAndLastName(firstName, lastName);
    }

    public boolean checkIsEmailFree(String email) throws ServiceException {
        try {
            return !(userDao.findUserByEmail(email).isPresent());
        } catch (DaoException e) {
            throw new ServiceException("Problem with find user by email in user service");
        }
    }

    @Override
    public void register(String email, String password, String firstName, String lastName) throws ServiceException {
        String passwordHashed = BCryptHash.hashPassword(password);
        User user = new User(email, firstName, lastName, Role.USER, AccountStatus.BANNED);
        try {
            userDao.create(user, passwordHashed);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method create in user service", e);
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
            throw new ServiceException("Problem with log in in user service", e);
        }
    }

    @Override
    public void addBalance(long userId, BigDecimal amount) throws ServiceException {
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
    public Optional<User> findUserById(long userId) throws ServiceException {
        try {
            return userDao.findEntityById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findUserById in user service", e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean isUpdated = false;
        if (UserFormValidator.isValidEmail(user.getEmail()) && UserFormValidator.isValidFirstAndLastName(user.getFirstName(),
                user.getLastName())) {
            try {
                userDao.updateUserInformation(user);
                isUpdated = true;
            } catch (DaoException e) {
                throw new ServiceException("Problem in method updateUserInformation in user service", e);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserPassword(long userId, String oldPassword, String newPassword, String confirmPassword) throws ServiceException {
        boolean isUpdated = false;
        if (!confirmPassword.equals(newPassword)){
            return isUpdated;
        }
        String userPassword = getUserPassword(userId);
        if (!oldPassword.equals(newPassword) && UserFormValidator.isValidPassword(oldPassword) && UserFormValidator.isValidPassword(newPassword)
                && BCryptHash.checkPassword(oldPassword, userPassword)) {
            try {
                userDao.updateUserPassword(userId, BCryptHash.hashPassword(newPassword));
            } catch (DaoException e) {
                throw new ServiceException("Problem with updating password in user service", e);
            }
            isUpdated = true;
        }
        return isUpdated;
    }

    private String getUserPassword(long userId) throws ServiceException {
        String userPassword;
        try {
            userPassword = userDao.findPasswordById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Problem with find user password in user service", e);
        }
        return userPassword;
    }

    @Override
    public boolean deleteAccount(long userId, int statusId, String password) throws ServiceException {
        boolean isDeleted = false;
        String userPassword = getUserPassword(userId);
        if (BCryptHash.checkPassword(password, userPassword)) {
            try {
                userDao.changeAccountStatus(userId, statusId);
            } catch (DaoException e) {
                throw new ServiceException("Problem with method delete account in user service", e);
            }
            isDeleted = true;
        }

        return isDeleted;
    }

    @Override
    public void changeAccountStatus(long userId, int statusId) throws ServiceException {
        try {
            userDao.changeAccountStatus(userId, statusId);
        } catch (DaoException e) {
            throw new ServiceException("Problem with changing account status", e);
        }
    }

    @Override
    public boolean activateAccount(String email) throws ServiceException {
        boolean isCommandSuccess = false;
        try {
            Optional<User> user = userDao.findUserByEmail(email);
            if (user.isPresent()) {
                long userId = user.get().getId();
                userDao.changeAccountStatus(userId, ACTIVE_STATUS_ACCOUNT);
                isCommandSuccess = true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Problem with activate account in user service", e);
        }
        return isCommandSuccess;
    }
}

