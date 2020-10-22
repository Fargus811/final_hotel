package by.sergeev.hotel.controller.command;

import by.sergeev.hotel.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request) throws CommandException;
}

