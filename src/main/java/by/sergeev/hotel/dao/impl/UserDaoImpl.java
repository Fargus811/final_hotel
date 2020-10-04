package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.UserDao;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    @Override
    public User findEntityById(int id) throws DaoException {
        return null;
    }

    @Override
    public void create(User user) throws DaoException {

    }

    @Override
    public void update(User user) throws DaoException {

    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        return null;
    }

    @Override
    public void updatePassword(String login, String password) throws DaoException {

    }

    @Override
    public void authorization(String login, String password) throws DaoException {

    }

    @Override
    public void updateEmail(String login, String email) throws DaoException {

    }
}
