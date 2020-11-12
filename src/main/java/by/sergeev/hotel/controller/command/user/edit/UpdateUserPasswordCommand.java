package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.EditCommand;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateUserPasswordCommand implements EditCommand {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        String oldPassword = request.getParameter(PageParameter.OLD_PASSWORD);
        String newPassword = request.getParameter(PageParameter.NEW_PASSWORD);
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) (session.getAttribute(PageParameter.SESSION_USER));
        int userId = sessionUser.getId();
        boolean isCommandSuccess;
        try {
            isCommandSuccess =  userService.updateUserPassword(userId,oldPassword,newPassword);
        } catch (ServiceException e) {
            throw new CommandException("Problem with update user password", e);
        }
        if (isCommandSuccess) {
            session.invalidate();
            result = PagePath.LOGIN;
        }else {
            request.setAttribute(PageParameter.ERROR_INFO, PageParameter.ERROR);
            result = CommandType.SHOW_PROFILE_SETTINGS.name();
        }
        return result;
    }
}
