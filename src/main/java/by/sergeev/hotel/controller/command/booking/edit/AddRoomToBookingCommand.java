package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * The type Add room to booking command as param.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class AddRoomToBookingCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        long roomId = Long.parseLong(request.getParameter(PageParameter.ROOM_ID));
        BigDecimal totalCost = new BigDecimal(request.getParameter(PageParameter.COST));
        try {
            bookingService.addRoomToBooking(bookingId, roomId, totalCost);
        } catch (ServiceException e) {
            throw new CommandException("Problem with adding room to booking", e);
        }
        return PagePath.INFO_SUCCESS;
    }
}
