package by.sergeev.hotel.controller.command.user.edit;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.PageParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(PageParameter.LOCALE_PARAM);
        HttpSession session = request.getSession(true);
        session.setAttribute(PageParameter.LOCALE, locale);
        String lastShowCommandName =session.getAttribute("lastShowCommandName").toString();
        Map<String,String[]>  parameters =(Map<String, String[]>)session.getAttribute("lastShowCommandParameters");
        if (Objects.isNull(parameters) || parameters == null){
            String curPage = PagePath.INDEX;
            return curPage;
        }
        return CommandType.valueOf(lastShowCommandName).name();
    }

    public HttpServletRequest setLastShowCommandParameters(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Map<String,String[]>  parameters =(Map<String, String[]>)session.getAttribute("lastShowCommandParameters");
        return new HttpServletRequestWrapper(request){
            @Override
            public String getParameter(String name) {
                if (parameters.containsKey(name)){
                    return parameters.get(name)[0];
                }
                return super.getParameter(name);
            }
        };
    }
}
