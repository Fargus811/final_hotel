package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private static final String REQUEST_PARAMETER_EMAIL = "email";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REQUEST_PARAMETER_FIRST_NAME = "firstName";
    private static final String REQUEST_PARAMETER_LAST_NAME = "lastName";

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(REQUEST_PARAMETER_EMAIL);
        String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
        String firstName = request.getParameter(REQUEST_PARAMETER_FIRST_NAME);
        String lastName = request.getParameter(REQUEST_PARAMETER_LAST_NAME);
        String page = Page.REGISTRATION;

        boolean isParamsValid = userService.checkIsValid(email, password, firstName, lastName);

        if (isParamsValid) {
            userService.register(email, password, firstName, lastName);
            page = Page.LOGIN;
        }
        return page;
    }
}
