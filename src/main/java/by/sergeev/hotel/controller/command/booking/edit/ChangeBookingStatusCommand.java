package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Change booking status command in booking.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ChangeBookingStatusCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int bookingStatus = Integer.parseInt(request.getParameter(PageParameter.BOOKING_STATUS_ID));
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        try {
            bookingService.changeBookingStatusById(bookingId, bookingStatus);
        } catch (ServiceException e) {
            throw new CommandException("Problem with changeBookingStatusById", e);
        }
        return PagePath.INFO_SUCCESS;
    }
}
