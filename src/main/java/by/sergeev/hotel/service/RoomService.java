package by.sergeev.hotel.service;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;

import java.util.List;

public class RoomService {
    public List<Room> findAll() throws ServiceException {
        RoomDao roomDao = DaoFactory.getInstance().getRoomDao();
        List<Room> rooms = null;
        try {
            rooms = roomDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return rooms;
    }
}
