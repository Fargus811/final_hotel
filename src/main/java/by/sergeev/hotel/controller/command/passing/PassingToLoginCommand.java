package by.sergeev.hotel.controller.command.passing;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Passing to login page.
 */
public class PassingToLoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PagePath.LOGIN;
    }
}
