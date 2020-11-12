package by.sergeev.hotel.controller.command.room.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class DeleteRoomCommand implements Command {

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String roomId = request.getParameter(PageParameter.ROOM_ID);
        try {
            roomService.deleteRoom(roomId);
        } catch (ServiceException e) {
            throw new CommandException("Problem with delete room ", e);
        }
        return null;
    }
}
