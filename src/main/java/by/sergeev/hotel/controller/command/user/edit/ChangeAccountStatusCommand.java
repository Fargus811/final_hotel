package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangeAccountStatusCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String userId = request.getParameter(PageParameter.USER_ID);
        String status = request.getParameter(PageParameter.ACCOUNT_STATUS);

        return null;
    }
}
