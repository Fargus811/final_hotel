package by.sergeev.hotel.controller;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandDefiner;
import by.sergeev.hotel.controller.command.CommandType;
import by.sergeev.hotel.controller.command.ShowCommand;
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
    private static final Logger LOGGER = LogManager.getLogger();


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
            String commandResult = null;
            CommandDefiner commandDefiner = new CommandDefiner();
            Command command = commandDefiner.define(request);
//            if (command instanceof ShowCommand){
//            Map<String,Object> map = command.getParameters();
//            String name = command.getName();
//            HttpSession session = request.getSession(true);
//            session.setAttribute();}
            commandResult = command.execute(request);
            if (commandResult.endsWith("jsp")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(commandResult);
                dispatcher.forward(request, response);
            }else if(commandResult.startsWith("show")){
                Command showCommand =CommandType.valueOf(commandResult.toUpperCase()).getCommand();
                String page  = showCommand.execute(request);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }else {
                throw new RuntimeException("Not supported command result");
            }
        } catch (CommandException  e) {
            LOGGER.error("Command failed", e);
            throw new ServletException("Command failed", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAll();
        super.destroy();
    }
}
