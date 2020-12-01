package by.sergeev.hotel.controller.command.booking.show;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * The type Show detail of user's booking.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class SeeDetailsOfBookingCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();
    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long roomId = Long.parseLong(request.getParameter(PageParameter.ROOM_ID));
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        BigDecimal totalCost;
        Optional<Room> room;
        Optional<Booking> booking;
        try {
            totalCost = bookingService.getTotalCostOfBooking(bookingId, roomId);
            room = roomService.findRoomById(roomId);
            booking = bookingService.findBookingById(bookingId);
        } catch (ServiceException e) {
            throw new CommandException("Problem with find room by id", e);
        }
        if (booking.isPresent() && room.isPresent()) {
            request.setAttribute(PageParameter.COST, totalCost);
            request.setAttribute(PageParameter.ROOM, room.get());
            request.setAttribute(PageParameter.BOOKING, booking.get());
        }
        return PagePath.ACCEPT_ROOM_TO_BOOKING;
    }
}
