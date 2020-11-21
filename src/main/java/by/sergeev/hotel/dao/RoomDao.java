package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RoomDao {

    List<Room> findAll() throws DaoException;

    Optional<Room> findRoomById(long id) throws DaoException;

    List<Room> findFreeRoomsByBooking(Booking booking) throws DaoException;

    void createRoom(Room room) throws DaoException;

    void deleteRoomById(long roomId) throws DaoException;

    void updateRoomInfo(Room room) throws DaoException;

    void updateRoomPhoto(long roomId, String newPath) throws DaoException;

    int getCountOfRows() throws DaoException;

    List<Room> getRoomsByPage(int offset) throws DaoException;
}
