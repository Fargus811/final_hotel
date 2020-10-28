package by.sergeev.hotel.controller.command.booking;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.EditCommand;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.enums.RoomGrade;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.util.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;


public class CreateBookingCommand implements Command, EditCommand {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public Object execute(HttpServletRequest request) {
        //TODO session if-else
        HttpSession session = request.getSession(true);
        SessionUser sessionUser = (SessionUser) (session.getAttribute("sessionUser"));
        int userId = sessionUser.getId();
        String startDate = request.getParameter(RequestParameter.START_DATE);
        String endDate = request.getParameter(RequestParameter.END_DATE);
        int maxPersons = Integer.parseInt(request.getParameter(RequestParameter.MAX_PERSONS));
        int numberOfBeds = Integer.parseInt(request.getParameter(RequestParameter.NUMBER_OF_BEDS));
        int gradeId =Integer.parseInt(request.getParameter(RequestParameter.GRADE_ID));
        int numberOfRooms = Integer.parseInt(request.getParameter(RequestParameter.NUMBER_OF_ROOMS));
        boolean hasWifi = false;
        if (Objects.isNull(request.getParameter(RequestParameter.HAS_WIFI))) {
            hasWifi = true;
        }
        boolean hasTV = false;
        if (Objects.isNull(request.getParameter(RequestParameter.HAS_TV))) {
            hasTV = true;
        }
        boolean hasBathroom = false;
        if (Objects.isNull(request.getParameter(RequestParameter.HAS_BATHROOM))) {
            hasBathroom = true;
        }
        Booking freshBooking = new Booking();
        freshBooking.setUserId(userId);
        freshBooking.setStartDate(startDate);
        freshBooking.setEndDate(endDate);
        freshBooking.setCost(0.0);
        freshBooking.setMaxPersons(maxPersons);
        freshBooking.setNumberOfBeds(numberOfBeds);
        freshBooking.setNumberOfRooms(numberOfRooms);
        freshBooking.setRoomGrade(RoomGrade.getRoomGrade(gradeId));
        freshBooking.setHasWifi(hasWifi);
        freshBooking.setHasTV(hasTV);
        freshBooking.setHasBathroom(hasBathroom);

        bookingService.createBooking(freshBooking);

        return CommandType.SHOW_USER_BOOKINGS.getCommand();
    }

}
