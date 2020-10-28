package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findEntityById(int id);

    void create(User user, String password);

    Optional<User> findUserByEmail(String email);

    String findPasswordById(int userId);

    void updateEntity(User entity, String password);
}
