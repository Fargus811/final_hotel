package by.sergeev.hotel.controller.command.booking.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class PayForBookingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long bookingId = Long.parseLong(request.getParameter(PageParameter.BOOKING_ID));
        BigDecimal totalCost = new BigDecimal(request.getParameter(PageParameter.COST));

        return null;
    }
}
