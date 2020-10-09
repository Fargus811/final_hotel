package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.util.List;

public interface UserDao {

     List<User> findAll() throws DaoException;
     User findEntityById(int id) throws DaoException;
     User findUserByLogin(String login, ProxyConnection proxyConnection) throws DaoException;
     void updatePassword(String login, String password) throws DaoException;
     void authorization(String login, String password) throws DaoException;
     void updateEmail(String login, String email) throws DaoException;
}
