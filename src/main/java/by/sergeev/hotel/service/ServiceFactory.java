package by.sergeev.hotel.service;

import by.sergeev.hotel.service.impl.BookingServiceImpl;
import by.sergeev.hotel.service.impl.RoomServiceImpl;
import by.sergeev.hotel.service.impl.UserServiceImpl;

public class ServiceFactory {

    public static final ServiceFactory serviceFactory = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    private final BookingService bookingService = new BookingServiceImpl();

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }
}
