package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.EditCommand;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserPasswordCommand implements EditCommand {

    private UserService userService = ServiceFactory.serviceFactory.getUserService();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        return null;
    }
}
