package by.sergeev.hotel.controller.command.room;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.util.PagePath;
import by.sergeev.hotel.util.RequestParameter;

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

        rooms = roomService.findAll();

        if (Objects.isNull(session.getAttribute(RequestParameter.LOCALE))) {
            request.setAttribute(RequestParameter.LOCALE, RequestParameter.VALUE_OF_LOCALE);
        }
        request.setAttribute(RequestParameter.ROOMS_ATTRIBUTE, rooms);
        String page = PagePath.MAIN;
        return page;
    }
}
