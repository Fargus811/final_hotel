package by.sergeev.hotel.command.impl;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.utils.PageAttribute;

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
        String curPage = PageAttribute.INDEX_PAGE_ATTRIBUTE;
        return curPage;
    }
}
