package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class CalculateBookingCostCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();
    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long roomId = Long.parseLong(request.getParameter(PageParameter.ROOM_ID));
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        BigDecimal totalCost;
        try {
            totalCost = bookingService.getTotalCostOfBooking(bookingId, roomId);
        } catch (ServiceException e) {
            throw new CommandException("Problem with get total cost in command", e);
        }
        Room room;
        try {
            room = roomService.findRoomById(roomId);
        } catch (ServiceException e) {
            throw new CommandException("Problem with find room by id", e);
        }
        request.setAttribute(PageParameter.COST, totalCost);
        request.setAttribute(PageParameter.ROOM, room);
        request.setAttribute(PageParameter.BOOKING_ID, bookingId);
        return PagePath.ACCEPT_ROOM_TO_BOOKING;
    }
}
