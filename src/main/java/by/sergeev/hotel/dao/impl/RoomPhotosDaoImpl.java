package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.RoomPhotosDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.RoomPhoto;
import by.sergeev.hotel.exception.DaoException;

public class RoomPhotosDaoImpl implements RoomPhotosDao {
    @Override
    public RoomPhoto findPhotoByRoomId(int room_id) throws DaoException {
        return null;
    }

    @Override
    public void update(RoomPhoto roomPhoto) throws DaoException {

    }

    @Override
    public void delete(RoomPhoto roomPhoto) throws DaoException {

    }

    @Override
    public void create(Room room) throws DaoException {

    }
}
