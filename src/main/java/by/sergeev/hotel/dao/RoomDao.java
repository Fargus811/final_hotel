package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;

import java.util.List;

public interface RoomDao {

    List<Room> findAll() throws DaoException;
    Room findRoomById(int id) throws DaoException;
    Room sortRoomByCost(double cost) throws DaoException;
    void create(Room room) throws DaoException;
    void update(Room room) throws DaoException;
    Room sortRoomsByGrade() throws DaoException;
    Room sortRoomsByPeopleCount() throws DaoException;
}
