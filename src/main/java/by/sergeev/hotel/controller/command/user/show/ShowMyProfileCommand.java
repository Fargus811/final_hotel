package by.sergeev.hotel.controller.command.user.show;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class ShowMyProfileCommand implements Command {

    private final static String USER_INFO = "user";
    private final static String SESSION_USER = "sessionUser";
    private final static String PROFILE_SETTINGS = "profile";

    private UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        String profile = request.getParameter(PROFILE_SETTINGS);
        SessionUser sessionUser = (SessionUser) session.getAttribute(SESSION_USER);
        User user = userService.findUserById(sessionUser.getId());
        if (Objects.isNull(user)) {
            return PagePath.INDEX;//TODO error page
        } else {
            request.setAttribute(USER_INFO, user);
        }
        if (Objects.isNull(request.getParameter(PROFILE_SETTINGS))) {
            return PagePath.CLIENT_PROFILE;
        } else {
            return PagePath.CLIENT_SETTINGS;
        }
    }
}
