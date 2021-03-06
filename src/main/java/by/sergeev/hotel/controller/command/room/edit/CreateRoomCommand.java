package by.sergeev.hotel.controller.command.room.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.enumeration.RoomGrade;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Create room command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class CreateRoomCommand implements Command {

    private RoomService roomService = ServiceFactory.serviceFactory.getRoomService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        HttpSession session = request.getSession();
        String fileName = session.getAttribute(PageParameter.DOWNLOAD_STATUS).toString();
        String roomName = request.getParameter(PageParameter.ROOM_NAME);
        String roomDescription = request.getParameter(PageParameter.ROOM_DESCRIPTION);
        double cost = Double.parseDouble(request.getParameter(PageParameter.ROOM_COST));
        int gradeId = Integer.parseInt(request.getParameter(PageParameter.GRADE_ID));
        int numberOfRooms = Integer.parseInt(request.getParameter(PageParameter.NUMBER_OF_ROOMS));
        int numberOfBeds = Integer.parseInt(request.getParameter(PageParameter.NUMBER_OF_BEDS));
        int maxPersons = Integer.parseInt(request.getParameter(PageParameter.MAX_PERSONS));
        boolean hasWifi = false;
        if (!Objects.isNull(request.getParameter(PageParameter.HAS_WIFI))) {
            hasWifi = true;
        }
        boolean hasTV = false;
        if (!Objects.isNull(request.getParameter(PageParameter.HAS_TV))) {
            hasTV = true;
        }
        boolean hasBathroom = false;
        if (!Objects.isNull(request.getParameter(PageParameter.HAS_BATHROOM))) {
            hasBathroom = true;
        }
        Room room = new Room();
        room.setName(roomName);
        room.setDescription(roomDescription);
        room.setPhotoPath(fileName);
        room.setCost(BigDecimal.valueOf(cost));
        room.setMaxPersons(maxPersons);
        room.setNumberOfBeds(numberOfBeds);
        room.setNumberOfRooms(numberOfRooms);
        room.setRoomGrade(RoomGrade.values()[gradeId]);
        room.setHasWifi(hasWifi);
        room.setHasTV(hasTV);
        room.setHasBathroom(hasBathroom);
        boolean isCommandSuccess;
        try {
            isCommandSuccess = roomService.createRoom(room);
        } catch (ServiceException e) {
            throw new CommandException("Problem with create booking", e);
        }
        if (isCommandSuccess) {
            result = PagePath.INFO_SUCCESS;
            session.removeAttribute(PageParameter.DOWNLOAD_STATUS);
        } else {
            request.setAttribute(PageParameter.ERROR, PageParameter.ERROR);
            result = PagePath.CREATE_ROOM;
        }
        return result;
    }

}
