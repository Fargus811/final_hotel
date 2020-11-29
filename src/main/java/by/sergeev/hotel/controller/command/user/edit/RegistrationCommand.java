package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.util.mail.ClientNotificationSender;
import by.sergeev.hotel.util.mail.impl.ClientNotificationSenderImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Registration command.
 */
public class RegistrationCommand implements Command {

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(PageParameter.EMAIL);
        String password = request.getParameter(PageParameter.PASSWORD);
        String firstName = request.getParameter(PageParameter.FIRST_NAME);
        String lastName = request.getParameter(PageParameter.LAST_NAME);
        String page = PagePath.REGISTRATION;
        boolean isParamsValid;
        boolean isEmailFree;
        isParamsValid = userService.checkIsValid(email, password, firstName, lastName);
        if (isParamsValid) {
            try {
                isEmailFree = userService.checkIsEmailFree(email);
            } catch (ServiceException e) {
                throw new CommandException("Problem with checking email free", e);
            }
            if (isEmailFree) {
                try {
                    ClientNotificationSender clientNotificationSender = new ClientNotificationSenderImpl();
                    clientNotificationSender.registerMailNotification(email,
                            firstName, request.getRequestURL().toString());
                    userService.register(email, password, firstName, lastName);
                } catch (ServiceException e) {
                    throw new CommandException("Problem with registration", e);
                }
                page = PagePath.INFO_SUCCESS;
            } else {
                request.setAttribute(PageParameter.ERROR_EMAIL, PageParameter.ERROR_INFO);
            }
        } else {
            request.setAttribute(PageParameter.ERROR_PARAMETERS, PageParameter.ERROR_INFO);
        }
        return page;
    }
}
