package by.sergeev.hotel.controller.command.booking;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class ChangeBookingStatusCommand implements Command {

    private static final String REQUEST_PARAMETER_BOOKING_STATUS = "booking_status";
    private static final String REQUEST_PARAMETER_BOOKING_ID = "booking_id";

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String bookingStatus = request.getParameter(REQUEST_PARAMETER_BOOKING_STATUS);
        int bookingId = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_BOOKING_ID));

        bookingService.changeBookingStatusById(bookingId, bookingStatus);

        return null;
    }
}
