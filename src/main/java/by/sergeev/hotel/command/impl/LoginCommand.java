package by.sergeev.hotel.command.impl;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.utils.PageAttribute;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with UserService, while trying to logIn", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException {
        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.logIn(email, password);
        boolean isCommandFailed = (user == null);
        //  packAttributes(login, password, user, isCommandFailed, request);
        String page = choosePage(isCommandFailed);
        return page;
    }

    private String choosePage(boolean isCommandFailed) {
        String page= isCommandFailed ? PageAttribute.LOGIN_PAGE_ATTRIBUTE : PageAttribute.CLIENT_MAIN_PAGE_ATTRIBUTE;
        return page;
    }
}
