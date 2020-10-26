package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.util.PagePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String USER_PARAM = "user";
    private static final String SESSION_USER_PARAM = "sessionUser";
    private static final String ERROR_PARAM = "error";

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        User user = userService.logIn(email, password);
        boolean isCommandSuccess = (user != null);
        HttpSession session = request.getSession(true);
        if (isCommandSuccess) {
            SessionUser sessionUser = new SessionUser(user.getId(),user.getFirstName(),user.getLastName(),user.getRole());
            session.setAttribute(SESSION_USER_PARAM, sessionUser);
            request.setAttribute(USER_PARAM, user);
            LOGGER.info(sessionUser + " logged in");
        } else {
            request.setAttribute(ERROR_PARAM, "error");
            LOGGER.info("Log in was failed");
        }
        return isCommandSuccess ? PagePath.CLIENT_PROFILE : PagePath.LOGIN;
    }
}


