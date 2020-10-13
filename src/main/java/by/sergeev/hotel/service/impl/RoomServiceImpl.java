package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import by.sergeev.hotel.service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    public List<Room> findAll() throws ServiceException {
        RoomDao roomDao = DaoFactory.getInstance().getRoomDao();
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return roomDao.findAll(connection);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException("Problem, when trying find all rooms", e);
        }
    }
}
