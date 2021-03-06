package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.*;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Update user password command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class UpdateUserPasswordCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        String oldPassword = request.getParameter(PageParameter.OLD_PASSWORD);
        String newPassword = request.getParameter(PageParameter.NEW_PASSWORD);
        String confirmPassword = request.getParameter(PageParameter.CONFIRM_PASSWORD);
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) (session.getAttribute(PageParameter.SESSION_USER));
        long userId = sessionUser.getId();
        boolean isCommandSuccess;
        try {
            isCommandSuccess = userService.updateUserPassword(userId, oldPassword, newPassword, confirmPassword);
        } catch (ServiceException e) {
            throw new CommandException("Problem with update user password", e);
        }
        if (isCommandSuccess) {
            session.invalidate();
            result = PagePath.LOGIN;
        } else {
            request.setAttribute(PageParameter.ERROR_INFO, PageParameter.ERROR);
            result = CommandType.SHOW_PROFILE_SETTINGS.name();
        }
        return result;
    }
}
