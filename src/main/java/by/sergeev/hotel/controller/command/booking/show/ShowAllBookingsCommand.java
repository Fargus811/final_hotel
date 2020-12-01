package by.sergeev.hotel.controller.command.booking.show;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The type Show all users bookings command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ShowAllBookingsCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Booking> bookings;
        try {
            bookings = bookingService.findAll();
        } catch (ServiceException e) {
            throw new CommandException("Problem with find all bookings", e);
        }
        request.setAttribute(PageParameter.BOOKINGS, bookings);
        return PagePath.ALL_BOOKINGS;
    }
}
