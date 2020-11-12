package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.util.List;
import java.util.Optional;

public interface RoomDao {

    List<Room> findAll() throws DaoException;

    Optional<Room> findRoomById(int id) throws DaoException;

    List<Room> findFreeRoomsByBooking(Booking booking) throws DaoException;

    Room sortRoomByCost(double cost) throws DaoException;

    Room sortRoomsByGrade() throws DaoException;

    Room sortRoomsByPeopleCount() throws DaoException;

    void createRoom(Room room) throws DaoException;

    void deleteRoomById(int roomId) throws DaoException;
}
