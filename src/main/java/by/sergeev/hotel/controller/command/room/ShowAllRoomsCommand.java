package by.sergeev.hotel.controller.command.room;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class ShowAllRoomsCommand implements Command {

    private static final String ROOMS_ATTRIBUTE = "rooms";
    private static final String ATTRIBUTE_LOCALE = "locale";
    private static final String VALUE_OF_LOCALE = "ru";

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        List<Room> rooms;

        rooms = roomService.findAll();

        if (Objects.isNull(session.getAttribute(ATTRIBUTE_LOCALE))) {
            request.setAttribute(ATTRIBUTE_LOCALE, VALUE_OF_LOCALE);
        }
        request.setAttribute(ROOMS_ATTRIBUTE, rooms);
        String page = Page.MAIN;
        return page;
    }
}
