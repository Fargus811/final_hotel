package by.sergeev.hotel.controller.command.booking;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.enums.RoomGrade;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;


public class CreateBookingCommand implements Command {

    private static final String REQUEST_PARAMETER_START_DATE = "startDate";
    private static final String REQUEST_PARAMETER_END_DATE = "endDate";
    private static final String REQUEST_PARAMETER_COST = "cost";
    private static final String REQUEST_PARAMETER_MAX_PERSONS = "maxPersons";
    private static final String REQUEST_PARAMETER_NUMBER_OF_BEDS = "numberOfBeds";
    private static final String REQUEST_PARAMETER_NUMBER_OF_ROOMS = "numberOfRooms";
    private static final String REQUEST_PARAMETER_FLOOR = "floor";
    private static final String REQUEST_PARAMETER_GRADE_NAME = "gradeName";
    private static final String REQUEST_PARAMETER_HAS_WIFI = "hasWifi";
    private static final String REQUEST_PARAMETER_HAS_TV = "hasTV";
    private static final String REQUEST_PARAMETER_HAS_BATHROOM = "hasBathroom";

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) {
        //TODO session if-else
        HttpSession session = request.getSession(true);
        SessionUser sessionUser = (SessionUser) (session.getAttribute("sessionUser"));
        int userId = sessionUser.getId();
        String startDate = request.getParameter(REQUEST_PARAMETER_START_DATE);
        String endDate = request.getParameter(REQUEST_PARAMETER_END_DATE);
        int maxPersons = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_MAX_PERSONS));
        int numberOfBeds = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_NUMBER_OF_BEDS));
        int gradeId =Integer.parseInt(request.getParameter(REQUEST_PARAMETER_GRADE_NAME));
        int numberOfRooms = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_NUMBER_OF_ROOMS));
        boolean hasWifi = false;
        if (Objects.isNull(request.getParameter(REQUEST_PARAMETER_HAS_WIFI))) {
            hasWifi = true;
        }
        boolean hasTV = false;
        if (Objects.isNull(request.getParameter(REQUEST_PARAMETER_HAS_TV))) {
            hasTV = Boolean.getBoolean(request.getParameter(REQUEST_PARAMETER_HAS_TV));
        }
        boolean hasBathroom = false;
        if (Objects.isNull(request.getParameter(REQUEST_PARAMETER_HAS_BATHROOM))) {
            hasBathroom = Boolean.getBoolean(request.getParameter(REQUEST_PARAMETER_HAS_BATHROOM));
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

        return Page.CLIENT_PROFILE;
    }

}
