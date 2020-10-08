package by.sergeev.hotel.service;

import by.sergeev.hotel.service.impl.RoomServiceImpl;
import by.sergeev.hotel.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance;
    private static volatile Object localInstance = new Object();
    private final UserService userService = new UserServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (localInstance) {
                if (instance == null) {
                    instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public RoomService getRoomService() {
        return roomService;
    }
}
