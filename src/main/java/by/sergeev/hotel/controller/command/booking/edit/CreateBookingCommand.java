package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.enumeration.RoomGrade;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Create booking command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class CreateBookingCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        HttpSession session = request.getSession(true);
        SessionUser sessionUser = (SessionUser) (session.getAttribute(PageParameter.SESSION_USER));
        long userId = sessionUser.getId();
        String startDate = request.getParameter(PageParameter.START_DATE);
        String endDate = request.getParameter(PageParameter.END_DATE);
        int maxPersons = Integer.parseInt(request.getParameter(PageParameter.MAX_PERSONS));
        int numberOfBeds = Integer.parseInt(request.getParameter(PageParameter.NUMBER_OF_BEDS));
        int gradeId = Integer.parseInt(request.getParameter(PageParameter.GRADE_ID));
        int numberOfRooms = Integer.parseInt(request.getParameter(PageParameter.NUMBER_OF_ROOMS));
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
        Booking freshBooking = new Booking();
        freshBooking.setUserId(userId);
        freshBooking.setStartDate(startDate);
        freshBooking.setEndDate(endDate);
        freshBooking.setCost(BigDecimal.ZERO);
        freshBooking.setMaxPersons(maxPersons);
        freshBooking.setNumberOfBeds(numberOfBeds);
        freshBooking.setNumberOfRooms(numberOfRooms);
        freshBooking.setRoomGrade(RoomGrade.values()[gradeId]);
        freshBooking.setHasWifi(hasWifi);
        freshBooking.setHasTV(hasTV);
        freshBooking.setHasBathroom(hasBathroom);
        boolean isCommandSuccess;
        try {
            isCommandSuccess = bookingService.createBooking(freshBooking);
        } catch (ServiceException e) {
            throw new CommandException("Problem with create booking", e);
        }
        if (isCommandSuccess) {
            result = CommandType.SHOW_USER_BOOKINGS.name();
        } else {
            request.setAttribute(PageParameter.ERROR, PageParameter.ERROR);
            result = PagePath.CREATE_BOOKING;
        }
        return result;
    }
}
