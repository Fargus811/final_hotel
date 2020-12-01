package by.sergeev.hotel.dao;

import by.sergeev.hotel.dao.impl.BookingDaoImpl;
import by.sergeev.hotel.dao.impl.RoomDaoImpl;
import by.sergeev.hotel.dao.impl.UserDaoImpl;

/**
 * The type Dao factory.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class DaoFactory {

    /**
     * The constant daoFactory.
     */
    public static final DaoFactory daoFactory = new DaoFactory();
    private final UserDao userDao = new UserDaoImpl();
    private final RoomDao roomDao = new RoomDaoImpl();
    private final BookingDao bookingDao = new BookingDaoImpl();

    private DaoFactory(){
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets room dao.
     *
     * @return the room dao
     */
    public RoomDao getRoomDao() {
        return roomDao;
    }

    /**
     * Gets booking dao.
     *
     * @return the booking dao
     */
    public BookingDao getBookingDao() {
        return bookingDao;
    }
}
