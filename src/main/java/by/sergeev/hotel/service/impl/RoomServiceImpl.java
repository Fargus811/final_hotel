package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    private RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();

    public List<Room> findAll() throws ServiceException {
        return roomDao.findAll();
    }
}
