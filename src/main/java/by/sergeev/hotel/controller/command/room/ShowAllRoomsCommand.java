package by.sergeev.hotel.controller.command.room;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class ShowAllRoomsCommand implements Command {

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        List<Room> rooms;
        try {
            rooms = roomService.findAll();
        } catch (ServiceException e) {
            throw new CommandException("Problem with method findAll in room service", e);
        }
        if (Objects.isNull(session.getAttribute(PageParameter.LOCALE))) {
            request.setAttribute(PageParameter.LOCALE, PageParameter.VALUE_OF_LOCALE);
        }
        request.setAttribute(PageParameter.ROOMS, rooms);
        return PagePath.MAIN;
    }
}
