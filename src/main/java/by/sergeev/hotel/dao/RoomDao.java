package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.util.List;

public interface RoomDao {

    List<Room> findAll(ProxyConnection proxyConnection) throws DaoException;

    Room findRoomById(int id) throws DaoException;

    Room sortRoomByCost(double cost) throws DaoException;

    Room sortRoomsByGrade() throws DaoException;

    Room sortRoomsByPeopleCount() throws DaoException;

}
