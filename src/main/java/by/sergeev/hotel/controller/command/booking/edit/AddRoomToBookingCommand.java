package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;


import javax.servlet.http.HttpServletRequest;

public class AddRoomToBookingCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int bookingId = Integer.parseInt(request.getParameter(PageParameter.BOOKING_ID));
        int roomId = Integer.parseInt(request.getParameter(PageParameter.ROOM_ID));
        try{
            bookingService.addRoomToBooking(bookingId,roomId);
        }catch (ServiceException e){
            throw new CommandException("Problem with adding room to booking", e);
        }
        return CommandType.SHOW_USER_BOOKINGS.name();
    }
}
