package by.sergeev.hotel.controller;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandDefiner;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.user.edit.ChangeLanguageCommand;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private static final String RESULT_JSP = "jsp";
    private static final String RESULT_SHOW_COMMAND = "SHOW";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String commandResult;
            Command command = CommandDefiner.define(request);
            if (command instanceof ChangeLanguageCommand) {
                ChangeLanguageCommand changeLanguageCommand = (ChangeLanguageCommand) command;
                request = changeLanguageCommand.setLastPageAttributes(request);
            }
            commandResult = command.execute(request);
            if (commandResult.endsWith(RESULT_JSP)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(commandResult);
                saveLastPageNameAndAttributes(commandResult, request);
                dispatcher.forward(request, response);
            } else if (commandResult.startsWith(RESULT_SHOW_COMMAND)) {
                String showCommandName = commandResult.toUpperCase();
                Command showCommand = CommandType.valueOf(showCommandName).getCommand();
                String page = showCommand.execute(request);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                saveLastPageNameAndAttributes(page, request);
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(PagePath.ERROR);
            }
        } catch (CommandException e) {
            LOGGER.error("Command failed", e);
            response.sendRedirect(PagePath.ERROR);
        }
    }

    private void saveLastPageNameAndAttributes(String pageName, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("lastPageName", pageName);
        httpSession.setAttribute("lastPageAttributes", getAttributesMap(request));
        System.out.println(getAttributesMap(request));
    }

    private Map<String, Object> getAttributesMap(HttpServletRequest request) {
        Map<String, Object> mapAttributes = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            if (!name.equals("locale")) {
                mapAttributes.put(name, request.getAttribute(name));
            }
        }
        return mapAttributes;
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAll();
        super.destroy();
    }
}
