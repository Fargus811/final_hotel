package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The interface Room dao.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface RoomDao {

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Room> findAll() throws DaoException;

    /**
     * Find room by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Room> findRoomById(long id) throws DaoException;

    /**
     * Find free rooms by booking list.
     *
     * @param booking the booking
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Room> findFreeRoomsByBooking(Booking booking) throws DaoException;

    /**
     * Create room.
     *
     * @param room the room
     * @throws DaoException the dao exception
     */
    void createRoom(Room room) throws DaoException;

    /**
     * Delete room by id.
     *
     * @param roomId the room id
     * @throws DaoException the dao exception
     */
    void deleteRoomById(long roomId) throws DaoException;

    /**
     * Update room info.
     *
     * @param room the room
     * @throws DaoException the dao exception
     */
    void updateRoomInfo(Room room) throws DaoException;

    /**
     * Update room photo.
     *
     * @param roomId  the room id
     * @param newPath the new path
     * @throws DaoException the dao exception
     */
    void updateRoomPhoto(long roomId, String newPath) throws DaoException;

    /**
     * Gets count of rows.
     *
     * @return the count of rows
     * @throws DaoException the dao exception
     */
    int getCountOfRows() throws DaoException;

    /**
     * Gets rooms by page.
     *
     * @param offset the offset
     * @return the rooms by page
     * @throws DaoException the dao exception
     */
    List<Room> getRoomsByPage(int offset) throws DaoException;
}
