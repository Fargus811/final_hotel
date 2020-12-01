package by.sergeev.hotel.service;

import by.sergeev.hotel.service.impl.BookingServiceImpl;
import by.sergeev.hotel.service.impl.RoomServiceImpl;
import by.sergeev.hotel.service.impl.UserServiceImpl;

/**
 * The type Service factory.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ServiceFactory {

    /**
     * The constant serviceFactory.
     */
    public static final ServiceFactory serviceFactory = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    private final BookingService bookingService = new BookingServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets room service.
     *
     * @return the room service
     */
    public RoomService getRoomService() {
        return roomService;
    }

    /**
     * Gets booking service.
     *
     * @return the booking service
     */
    public BookingService getBookingService() {
        return bookingService;
    }
}
