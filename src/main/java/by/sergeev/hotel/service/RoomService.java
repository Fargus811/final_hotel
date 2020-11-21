package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.ServiceException;

import java.util.List;

public interface RoomService {

    List<Room> findAll() throws ServiceException;

    Room findRoomById(long roomId) throws ServiceException;

    List<Room> findFreeRoomsByBooking(long bookingId) throws ServiceException;

    boolean createRoom(Room room) throws ServiceException;

    void deleteRoom(String roomId) throws ServiceException;

    boolean updateRoomInfo(Room room) throws ServiceException;

    boolean updateRoomPhoto(long roomId, String fileName) throws ServiceException;

    List<Room> getRoomsByPage(int page) throws ServiceException;

    int getAmountOfPages() throws ServiceException;
}
