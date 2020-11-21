package by.sergeev.hotel.controller.command.room.show;

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

    private static final String NUMBER_OF_PAGE = "numberOfPage";
    private static final String HAS_PREV = "hasPrev";
    private static final String HAS_NEXT = "hasNext";
    private static final int FIRST_PAGE = 1;

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        List<Room> rooms;
        int page = Integer.parseInt(request.getParameter(NUMBER_OF_PAGE));
        try {
            int pages = roomService.getAmountOfPages();
            rooms = roomService.getRoomsByPage(page);
            request.setAttribute(NUMBER_OF_PAGE, page);
            request.setAttribute(HAS_NEXT, page < pages);
            request.setAttribute(HAS_PREV, page > FIRST_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Problem with method findAllUsers in room service", e);
        }
        if (Objects.isNull(session.getAttribute(PageParameter.LOCALE))) {
            request.setAttribute(PageParameter.LOCALE, PageParameter.VALUE_OF_LOCALE);
        }
        request.setAttribute(PageParameter.ROOMS, rooms);
        return PagePath.MAIN;
    }
}