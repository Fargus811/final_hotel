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
        System.out.println("findAll in service");
        RoomDao roomDao = DaoFactory.getInstance().getRoomDao();
        List<Room> rooms = null;
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()){
            rooms = roomDao.findAll(connection);
        } catch (DaoException e) {
            throw new ServiceException("Problem with RoomDAO, when trying to show all rooms", e);
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Problem with getting connection", e);
        }
        return rooms;
    }
}
