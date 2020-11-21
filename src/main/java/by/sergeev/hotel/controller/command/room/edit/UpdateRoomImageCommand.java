package by.sergeev.hotel.controller.command.room.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateRoomImageCommand implements Command {

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long roomId = Long.parseLong(request.getParameter(PageParameter.ROOM_ID));
        HttpSession session = request.getSession();
        String fileName = session.getAttribute(PageParameter.DOWNLOAD_STATUS).toString();
        try {
            roomService.updateRoomPhoto(roomId,fileName);
        } catch (ServiceException e) {
            throw new CommandException("Problem with updating room", e);
        }
        return PagePath.INDEX;
    }
}
