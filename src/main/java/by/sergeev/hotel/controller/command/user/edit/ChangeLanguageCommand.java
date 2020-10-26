package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    private static final String LOCALE_PARAM = "lang";
    private static final String LOCALE_ATTR = "locale";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(LOCALE_PARAM);
        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE_ATTR, locale);
        StringBuffer requestURL = request.getRequestURL();
        String requestURI = request.getRequestURI();
        String curPage = PagePath.INDEX;
        return curPage;
    }
}
