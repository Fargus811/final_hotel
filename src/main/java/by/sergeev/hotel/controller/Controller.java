package by.sergeev.hotel.controller;

import by.sergeev.hotel.controller.command.*;
import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
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
            commandResult = command.execute(request);
            if (commandResult.endsWith(RESULT_JSP)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(commandResult);
                dispatcher.forward(request, response);
            }else if(commandResult.startsWith(RESULT_SHOW_COMMAND)){
                Command showCommand =CommandType.valueOf(commandResult.toUpperCase()).getCommand();
                String page  = showCommand.execute(request);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }else {
                response.sendRedirect(PagePath.ERROR);
            }
        } catch (CommandException  e) {
            LOGGER.error("Command failed", e);
            response.sendRedirect(PagePath.ERROR);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAll();
        super.destroy();
    }
}
