package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(PageParameter.LOCALE_PARAM);
        System.out.println(locale);
        HttpSession session = request.getSession(true);
        session.setAttribute(PageParameter.LOCALE, locale);
        String lastPageName = session.getAttribute("lastPageName").toString();
        Map<String, String[]> parameters = (Map<String, String[]>) session.getAttribute("lastPageAttributes");
        if (Objects.isNull(parameters) || parameters == null) {
            String curPage = PagePath.INDEX;
            return curPage;
        }
        return lastPageName;
    }

    public HttpServletRequest setLastPageAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Map<String, Object> lastPageAttributes = (Map<String, Object>) session.getAttribute("lastPageAttributes");
        return new HttpServletRequestWrapper(request) {
            @Override
            public Object getAttribute(String name) {
                if (lastPageAttributes.containsKey(name)) {
                    return lastPageAttributes.get(name);
                }
                return super.getAttribute(name);
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return Collections.enumeration(lastPageAttributes.keySet());
            }
        };
    }
}
