package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteAccountCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) (session.getAttribute(PageParameter.SESSION_USER));
        int userId = sessionUser.getId();
        String password = request.getParameter(PageParameter.PASSWORD);
        boolean isCommandSuccess;
        try {
            isCommandSuccess = userService.deleteAccount(userId, password);
        } catch (ServiceException e) {
            throw new CommandException("Problem with method delete account in user service", e);
        }
        String result;
        if (isCommandSuccess) {
            session.invalidate();
            result = PagePath.INDEX;
        } else {
            result = CommandType.SHOW_PROFILE_SETTINGS.name();
        }
        return result;
    }
}
