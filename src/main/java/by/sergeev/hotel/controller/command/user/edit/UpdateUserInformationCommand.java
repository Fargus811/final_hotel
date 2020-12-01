package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.controller.command.PageParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Update user information command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class UpdateUserInformationCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) (session.getAttribute(PageParameter.SESSION_USER));
        long userId = sessionUser.getId();
        String email = request.getParameter(PageParameter.EMAIL);
        String firstName = request.getParameter(PageParameter.FIRST_NAME);
        String lastName = request.getParameter(PageParameter.LAST_NAME);
        User user = new User(userId,email,firstName,lastName);
        boolean isCommandSuccess;
        try {
           isCommandSuccess = userService.updateUser(user);
        } catch (ServiceException e) {
            throw new CommandException("Problem with user update", e);
        }
        if (isCommandSuccess) {
            result = CommandType.SHOW_MY_PROFILE.name();
            sessionUser.setFirstName(firstName);
            sessionUser.setLastName(lastName);
        }else {
            request.setAttribute(PageParameter.ERROR_INFO, PageParameter.ERROR);
            result = CommandType.SHOW_PROFILE_SETTINGS.name();
        }
        return result;
    }
}
