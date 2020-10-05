package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.RoomPhoto;
import by.sergeev.hotel.exception.DaoException;

public interface RoomPhotosDao {

    RoomPhoto findPhotoByRoomId(int room_id) throws DaoException;

    void update(RoomPhoto roomPhoto) throws DaoException;

    void delete(RoomPhoto roomPhoto) throws DaoException;

    void create(Room room) throws DaoException;
}
