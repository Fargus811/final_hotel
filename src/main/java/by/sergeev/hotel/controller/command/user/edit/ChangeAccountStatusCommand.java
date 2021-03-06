package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Change account status command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ChangeAccountStatusCommand implements Command {

    private static final int DELETE_STATUS_ACCOUNT = 2;

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        int userId = Integer.parseInt(request.getParameter(PageParameter.USER_ID));
        int statusId = Integer.parseInt(request.getParameter(PageParameter.ACCOUNT_STATUS));
        if (statusId != DELETE_STATUS_ACCOUNT) {
            try {
                userService.changeAccountStatus(userId, statusId);
            } catch (ServiceException e) {
                throw new CommandException("Problem with change account status", e);
            }
            result = PagePath.INFO_SUCCESS;
        } else {
            HttpSession session = request.getSession();
            String password = request.getParameter(PageParameter.PASSWORD);
            boolean isCommandSuccess;
            try {
                isCommandSuccess = userService.deleteAccount(userId, statusId, password);
            } catch (ServiceException e) {
                throw new CommandException("Problem with method delete account in user service", e);
            }
            if (isCommandSuccess) {
                session.invalidate();
                result = PagePath.INDEX;
            } else {
                result = CommandType.SHOW_PROFILE_SETTINGS.name();
                request.setAttribute(PageParameter.ERROR, PageParameter.ERROR_PARAMETERS);
            }
        }
        return result;
    }
}
