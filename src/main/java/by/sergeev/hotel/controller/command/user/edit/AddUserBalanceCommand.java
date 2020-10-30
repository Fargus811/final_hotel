package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserBalanceCommand implements Command {

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        double amount = Double.parseDouble(request.getParameter(RequestParameter.AMOUNT));
        HttpSession httpSession = request.getSession(true);
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(RequestParameter.SESSION_USER);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            userService.addBalance(sessionUser.getId(), amount, password);
        } catch (ServiceException e) {
            throw new CommandException("Problem with adding balance in user service", e);
        }
        return null;
    }
}
