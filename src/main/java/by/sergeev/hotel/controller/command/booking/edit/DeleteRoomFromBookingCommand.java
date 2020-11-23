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
 * The type Delete room from booking command.
 */
public class DeleteRoomFromBookingCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        boolean isCommandSuccess;
        try {
            isCommandSuccess = bookingService.deleteRoomFromBooking(bookingId);
        } catch (ServiceException e) {
            throw new CommandException("Problem with delete room from booking in command", e);
        }
        return isCommandSuccess? PagePath.INFO_SUCCESS : PagePath.INFO_FAIL;
    }
}
