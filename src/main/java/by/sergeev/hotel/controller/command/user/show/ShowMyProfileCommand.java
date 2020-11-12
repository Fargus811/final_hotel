package by.sergeev.hotel.controller.command.user.show;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowMyProfileCommand implements Command {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        SessionUser sessionUser = (SessionUser) session.getAttribute(PageParameter.SESSION_USER);
        Optional<User> user ;
        try {
            user = userService.findUserById(sessionUser.getId());
        } catch (ServiceException e) {
            throw new CommandException("Problem with find user by id", e);
        }
        if (!user.isPresent()) {
            return PagePath.ERROR;
        } else {
            request.setAttribute(PageParameter.USER, user.get());
            return PagePath.CLIENT_PROFILE;
        }
    }
}
