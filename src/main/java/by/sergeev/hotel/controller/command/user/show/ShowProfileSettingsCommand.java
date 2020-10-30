package by.sergeev.hotel.controller.command.user.show;

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
import java.util.Optional;

public class ShowProfileSettingsCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        SessionUser sessionUser = (SessionUser) session.getAttribute(RequestParameter.SESSION_USER);
        Optional<User> userOptional = userService.findUserById(sessionUser.getId());
        if (!userOptional.isPresent()) {
            return PagePath.ERROR;
        } else {
            request.setAttribute(RequestParameter.USER, userOptional.get());
            return PagePath.CLIENT_SETTINGS;
        }
    }
}
