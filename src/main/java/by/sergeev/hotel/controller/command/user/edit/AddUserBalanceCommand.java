package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.controller.command.PageParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * The type Refill user balance command.
 */
public class AddUserBalanceCommand implements Command {

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        double amount = Double.parseDouble(request.getParameter(PageParameter.AMOUNT));
        HttpSession httpSession = request.getSession(true);
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(PageParameter.SESSION_USER);
        try {
            userService.addBalance(sessionUser.getId(), BigDecimal.valueOf(amount));
        } catch (ServiceException e) {
            throw new CommandException("Problem with adding balance in user service", e);
        }
        return CommandType.SHOW_MY_PROFILE.name();
    }
}
