package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * The type Pay for booking command by user.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class PayForBookingCommand implements Command {

    private BookingService bookingService = ServiceFactory.serviceFactory.getBookingService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String resultPage;
        HttpSession httpSession = request.getSession();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(PageParameter.SESSION_USER);
        long userId = sessionUser.getId();
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        BigDecimal totalCost = new BigDecimal(request.getParameter(PageParameter.COST));
        boolean isCommandSuccess;
        try {
            isCommandSuccess = bookingService.payForBooking(userId, bookingId, totalCost);
        }catch (ServiceException e){
            throw new CommandException("Problem with payment for booking", e);
        }
        if (isCommandSuccess){
            resultPage = PagePath.INFO_SUCCESS;
        }else {
            resultPage = PagePath.INFO_FAIL;
        }
        return resultPage;
    }
}
