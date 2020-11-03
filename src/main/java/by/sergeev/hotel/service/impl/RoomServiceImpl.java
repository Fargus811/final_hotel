package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    private RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();

    public List<Room> findAll() throws ServiceException {
        try {
            return roomDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAll in roomDao", e);
        }
    }

    @Override
    public List<Room> findRoomsByBooking(int bookingId) throws ServiceException {
//        try {
//            return null;//roomDao.findRoomsByBooking(bookingId);
//        } catch (DaoException e) {
//            throw new ServiceException("Problem in method findAll in roomDao", e);
//        }
        return null;
    }
}
