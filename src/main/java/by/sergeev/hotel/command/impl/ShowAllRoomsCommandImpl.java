package by.sergeev.hotel.command.impl;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.utils.MappingPagesMannager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllRoomsCommandImpl implements Command {

    private static final String KEY_FOR_PAGE = "page.main";
    private static final String ROOMS_ATTRIBUTE = "rooms";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        RoomService roomService = new RoomService();
        List<Room> rooms;
        try {
             rooms = roomService.findAll();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(),e);
        }
        request.setAttribute(ROOMS_ATTRIBUTE,rooms);
        String page = MappingPagesMannager.getProperty(KEY_FOR_PAGE);
        return page;
    }
}
