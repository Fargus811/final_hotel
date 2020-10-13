package by.sergeev.hotel.command.impl;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.utils.PageAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class ShowAllRoomsCommandImpl implements Command {

    private static final String ROOMS_ATTRIBUTE = "rooms";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        RoomService roomService = ServiceFactory.getInstance().getRoomService();
        List<Room> rooms;
        try {
            rooms = roomService.findAll();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
        if (Objects.isNull(session.getAttribute("locale"))) {
            request.setAttribute("locale", "ru");
        }
        request.setAttribute(ROOMS_ATTRIBUTE, rooms);
        String page = PageAttribute.MAIN_PAGE_ATTRIBUTE;
        return page;
    }
}
