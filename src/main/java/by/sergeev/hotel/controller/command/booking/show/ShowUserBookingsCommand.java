package by.sergeev.hotel.controller.command.booking.show;

import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.ShowCommand;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class ShowUserBookingsCommand implements ShowCommand {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long userId;
        if (Objects.isNull(request.getParameter(PageParameter.USER_ID))) {
            HttpSession httpSession = request.getSession();
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute(PageParameter.SESSION_USER);
            userId = sessionUser.getId();
        } else {
            userId = Integer.parseInt(request.getParameter(PageParameter.USER_ID));
        }
        List<Booking> bookingList;
        try {
            bookingList = bookingService.findBookingsByUserId(userId);
        } catch (ServiceException e) {
            throw new CommandException("Problem with findBookingsById", e);
        }
        request.setAttribute(PageParameter.BOOKINGS, bookingList);
        return PagePath.USER_BOOKINGS;
    }
}
