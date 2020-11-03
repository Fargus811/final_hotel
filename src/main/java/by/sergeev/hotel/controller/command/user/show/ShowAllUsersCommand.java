package by.sergeev.hotel.controller.command.user.show;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.ServiceFactory;
import by.sergeev.hotel.service.UserService;
import by.sergeev.hotel.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllUsersCommand implements Command {

    private static final UserService userService = ServiceFactory.serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<User> users;
        try {
            users = userService.findAll();
        } catch (ServiceException e) {
            throw new CommandException("Problem with find all users", e);
        }
        request.setAttribute(RequestParameter.USERS, users);
        return null;
    }
}
