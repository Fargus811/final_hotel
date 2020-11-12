package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(PageParameter.LOCALE_PARAM);
        HttpSession session = request.getSession(true);
        session.setAttribute(PageParameter.LOCALE, locale);
        String curPage = PagePath.INDEX;
        return curPage;
    }
}
