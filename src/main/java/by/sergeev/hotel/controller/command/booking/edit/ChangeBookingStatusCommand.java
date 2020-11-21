package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.controller.command.PageParameter;

import javax.servlet.http.HttpServletRequest;

public class ChangeBookingStatusCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String bookingStatus = request.getParameter(PageParameter.BOOKING_STATUS);
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        try {
            bookingService.changeBookingStatusById(bookingId, bookingStatus);
        } catch (ServiceException e) {
            throw new CommandException("Problem with changeBookingStatusById", e);
        }
        return null;
    }
}