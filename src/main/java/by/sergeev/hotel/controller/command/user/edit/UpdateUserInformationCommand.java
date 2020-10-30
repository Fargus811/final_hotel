package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateUserInformationCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public Object execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) (session.getAttribute(RequestParameter.SESSION_USER));
        int userId = sessionUser.getId();
        String email = request.getParameter(RequestParameter.EMAIL);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        User user = new User(userId,email,firstName,lastName);
        userService.updateUser(user);

        String page = PagePath.REGISTRATION;
        return null;
    }
}
