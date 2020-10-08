package by.sergeev.hotel.dao;

import by.sergeev.hotel.dao.impl.RoomDaoImpl;
import by.sergeev.hotel.dao.impl.UserDaoImpl;

public class DaoFactory {

    private static DaoFactory instance;
    private static volatile Object localInstance = new Object();
    private final UserDao userDao = new UserDaoImpl();
    private final RoomDao roomDao = new RoomDaoImpl();

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (localInstance) {
                if (instance == null) {
                    instance = new DaoFactory();
                }
            }
        }
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }
}
