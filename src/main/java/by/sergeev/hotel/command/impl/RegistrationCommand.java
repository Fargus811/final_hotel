package by.sergeev.hotel.command.impl;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.utils.PageAttribute;
import by.sergeev.hotel.validator.RequestValidator;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private static final String REQUEST_PARAMETER_EMAIL = "email";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REQUEST_PARAMETER_FIRST_NAME = "firstName";
    private static final String REQUEST_PARAMETER_LAST_NAME = "lastName";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(REQUEST_PARAMETER_EMAIL);
        String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
        String firstName = request.getParameter(REQUEST_PARAMETER_FIRST_NAME);
        String lastName = request.getParameter(REQUEST_PARAMETER_LAST_NAME);
        String page = PageAttribute.REGISTRATION_PAGE_ATTRIBUTE;
        if (RequestValidator.isValidEmail(email) && RequestValidator.isValidPassword(password)) {
            UserService userService = ServiceFactory.getInstance().getUserService();
            boolean isLoginFree = false;
            try {
                isLoginFree = userService.checkIsEmailFree(email);
            } catch (ServiceException e) {
                throw new CommandException("Problem with checking email", e);
            }
            if (isLoginFree) {
                try {
                    userService.register(email, password, firstName, lastName);
                    page = PageAttribute.CLIENT_MAIN_PAGE_ATTRIBUTE;
                } catch (ServiceException e) {
                    throw new CommandException("Problem with registration", e);
                }
            }
        }

        return page;
    }
}