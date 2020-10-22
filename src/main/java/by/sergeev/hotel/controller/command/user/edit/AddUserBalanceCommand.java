package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserBalanceCommand implements Command {

    private static final String REQUEST_PARAMETER_AMOUNT = "amount";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String SESSION_PARAMETER_USER = "user";

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        double amount = Double.parseDouble(request.getParameter(REQUEST_PARAMETER_AMOUNT));
        HttpSession httpSession = request.getSession(true);
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(SESSION_PARAMETER_USER);
        String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
        userService.addBalance(sessionUser.getId(), amount, password);

        return null;
    }
}
