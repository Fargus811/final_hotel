package by.sergeev.hotel.command.impl;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
