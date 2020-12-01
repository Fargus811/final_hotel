package by.sergeev.hotel.controller.command.passing;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

/**
 * The type Passing to create room page.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class PassingToCreateRoom implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PagePath.CREATE_ROOM;
    }
}
