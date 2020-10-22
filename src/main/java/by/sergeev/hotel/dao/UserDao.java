package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao  {

    List<User> findAll();

    User findEntityById( int id);

    void create(User user, String password);

    User findUserByEmail(String email);

    String findPasswordById(int userId) ;

    void updateEntity(User entity, String password);
}
