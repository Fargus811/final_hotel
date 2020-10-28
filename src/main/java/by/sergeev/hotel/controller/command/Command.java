package by.sergeev.hotel.controller.command;

import by.sergeev.hotel.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Object execute(HttpServletRequest request) throws CommandException;
}

