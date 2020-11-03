package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.ServiceException;

import java.util.List;

public interface RoomService {

    List<Room> findAll() throws ServiceException;
    List<Room> findRoomsByBooking(int bookingId) throws ServiceException;
}
