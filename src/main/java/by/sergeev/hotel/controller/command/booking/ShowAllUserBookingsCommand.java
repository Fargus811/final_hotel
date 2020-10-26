package by.sergeev.hotel.controller.command.booking;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowAllUserBookingsCommand implements Command {

    private static final String SESSION_PARAMETER_USER = "sessionUser";
    private static final String REQUEST_PARAMETER_BOOKINGS = "bookings";

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession httpSession = request.getSession();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(SESSION_PARAMETER_USER);
        List<Booking> bookingList;

        bookingList = bookingService.findBookingsByUserId(sessionUser.getId());

        request.setAttribute(REQUEST_PARAMETER_BOOKINGS, bookingList);
        return PagePath.USER_BOOKINGS;
    }
}
