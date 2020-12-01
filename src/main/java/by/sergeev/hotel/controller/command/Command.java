package by.sergeev.hotel.controller.command;

import by.sergeev.hotel.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * The Command interface represents command.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface Command {

    /**
     * Execute command.
     *
     * @param request the request
     * @return the string containing page path
     */
    String execute(HttpServletRequest request) throws CommandException;
}

