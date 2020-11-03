package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String page = PagePath.REGISTRATION;
        boolean isParamsValid ;
        try {
            isParamsValid = userService.checkIsValid(email, password, firstName, lastName);
        } catch (ServiceException e) {
            throw new CommandException("Problem with validation registration params", e);
        }
        if (isParamsValid) {
            try {
                userService.register(email, password, firstName, lastName);
            } catch (ServiceException e) {
                throw new CommandException("Problem with registration", e);
            }
            page = PagePath.LOGIN;
        }
        return page;
    }
}
