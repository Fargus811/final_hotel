package by.sergeev.hotel.controller.command.room.show;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The type Show the room information to update command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ShowRoomToUpdateInfoCommand implements Command {

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long roomId = Long.parseLong(request.getParameter(PageParameter.ROOM_ID));
        Room room = null;
        try {
            Optional<Room> roomOptional = roomService.findRoomById(roomId);
            if (roomOptional.isPresent()) {
                room = roomOptional.get();
            }
        } catch (ServiceException e) {
            throw new CommandException("Problem with find room by id in room service", e);
        }
        request.setAttribute(PageParameter.ROOM, room);
        return PagePath.UPDATE_ROOM_INFO;
    }
}
