package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.util.Page;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        String page = Page.MAIN;
        return page;
    }
}

