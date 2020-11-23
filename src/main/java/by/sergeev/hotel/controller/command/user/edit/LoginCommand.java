package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Login command.
 */
public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final int ACCOUNT_STATUS_ACCESS = 0;

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(PageParameter.EMAIL);
        String password = request.getParameter(PageParameter.PASSWORD);
        Optional<User> userOptional;
        try {
            userOptional = userService.logIn(email, password);
        } catch (ServiceException e) {
            throw new CommandException("Problem with login", e);
        }
        boolean isCommandSuccess = userOptional.isPresent();
        String pagePath;
        if (isCommandSuccess) {
            User user = userOptional.get();
            if (user.getAccountStatus().ordinal() == ACCOUNT_STATUS_ACCESS) {
                HttpSession session = request.getSession(true);
                SessionUser sessionUser = new SessionUser(user.getId(), user.getFirstName(), user.getLastName(), user.getRole());
                session.setAttribute(PageParameter.SESSION_USER, sessionUser);
                request.setAttribute(PageParameter.USER, user);
                pagePath = PagePath.CLIENT_PROFILE;
                LOGGER.info(sessionUser + " logged in");
            } else {
                request.setAttribute(PageParameter.ERROR_BAN, PageParameter.ERROR);
                pagePath = PagePath.LOGIN;
            }
        } else {
            request.setAttribute(PageParameter.ERROR, PageParameter.ERROR);
            pagePath = PagePath.LOGIN;
            LOGGER.info("Log in was failed");
        }
        return pagePath;
    }
}


