package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
/**
 * The type Activate account after registration.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ActivateAccountAfterRegistrationCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(PageParameter.EMAIL);
        boolean isCommandSuccess;
        try {
            isCommandSuccess = userService.activateAccount(email);
        } catch (ServiceException e) {
            throw new CommandException("Problem in activate account in command", e);
        }
        return isCommandSuccess ? PagePath.INFO_SUCCESS : PagePath.INFO_FAIL;
    }
}
