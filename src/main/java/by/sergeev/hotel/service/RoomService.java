package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Room service.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface RoomService {

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Room> findAll() throws ServiceException;

    /**
     * Find room by id optional.
     *
     * @param roomId the room id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Room> findRoomById(long roomId) throws ServiceException;

    /**
     * Find free rooms by booking list.
     *
     * @param bookingId the booking id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Room> findFreeRoomsByBooking(long bookingId) throws ServiceException;

    /**
     * Create room boolean.
     *
     * @param room the room
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createRoom(Room room) throws ServiceException;

    /**
     * Delete room.
     *
     * @param roomId the room id
     * @throws ServiceException the service exception
     */
    void deleteRoom(long roomId) throws ServiceException;

    /**
     * Update room info boolean.
     *
     * @param room the room
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateRoomInfo(Room room) throws ServiceException;

    /**
     * Update room photo boolean.
     *
     * @param roomId   the room id
     * @param fileName the file name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateRoomPhoto(long roomId, String fileName) throws ServiceException;

    /**
     * Gets rooms by page.
     *
     * @param page the page
     * @return the rooms by page
     * @throws ServiceException the service exception
     */
    List<Room> getRoomsByPage(int page) throws ServiceException;

    /**
     * Gets amount of pages.
     *
     * @return the amount of pages
     * @throws ServiceException the service exception
     */
    int getAmountOfPages() throws ServiceException;
}
