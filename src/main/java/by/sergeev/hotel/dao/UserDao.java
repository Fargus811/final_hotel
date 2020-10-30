package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;

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
}
