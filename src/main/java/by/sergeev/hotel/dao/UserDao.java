package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll() throws DaoException;

    Optional<User> findEntityById(int id) throws DaoException;

    void create(User user, String password) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    String findPasswordById(int userId) throws DaoException;

    void updateEntity(User entity, String password) throws DaoException;

    void updateUserInformation(User user) throws DaoException;

    void updateUserPassword(int userId, String hashPassword) throws DaoException ;

    void deleteAccount(int userId) throws DaoException;

    void updateUserBalance(int userId, BigDecimal balance) throws DaoException;
}
