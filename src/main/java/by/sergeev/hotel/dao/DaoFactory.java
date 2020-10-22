package by.sergeev.hotel.dao;

import by.sergeev.hotel.dao.impl.BookingDaoImpl;
import by.sergeev.hotel.dao.impl.RoomDaoImpl;
import by.sergeev.hotel.dao.impl.UserDaoImpl;

public class DaoFactory {

    public static final DaoFactory daoFactory = new DaoFactory();
    private final UserDao userDao = new UserDaoImpl();
    private final RoomDao roomDao = new RoomDaoImpl();
    private final BookingDao bookingDao = new BookingDaoImpl();

    private DaoFactory(){
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public BookingDao getBookingDao() {
        return bookingDao;
    }
}
