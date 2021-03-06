package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Logout command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        String page = PagePath.INDEX;
        return page;
    }
}

