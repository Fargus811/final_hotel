package by.sergeev.hotel.controller.command.room;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowFreeRoomByConditionCommand implements Command {

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
       int bookingId = Integer.parseInt(request.getParameter(PageParameter.BOOKING_ID));
        List<Room> rooms;
        try {
            rooms = roomService.findFreeRoomsByBooking(bookingId);
        }catch (ServiceException e){
            throw new CommandException("Problem with find rooms by condition in room service", e);
        }
        request.setAttribute(PageParameter.ROOMS, rooms);
       return null;
    }
}
