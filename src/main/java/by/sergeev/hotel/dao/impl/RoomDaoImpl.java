package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.service.RoomService;

import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public List<Room> findAll() throws DaoException {
        return null;
    }

    @Override
    public Room findRoomById(int id) throws DaoException {
        return null;
    }

    @Override
    public Room sortRoomByCost(double cost) throws DaoException {
        return null;
    }

    @Override
    public void create(Room room) throws DaoException {

    }

    @Override
    public void update(Room room) throws DaoException {

    }

    @Override
    public Room sortRoomsByGrade() throws DaoException {
        return null;
    }

    @Override
    public Room sortRoomsByPeopleCount() throws DaoException {
        return null;
    }
}
