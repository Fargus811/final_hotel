package by.sergeev.hotel.controller;

import by.sergeev.hotel.controller.command.Command;
import by.sergeev.hotel.controller.command.CommandDefiner;
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
import java.io.IOException;

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
            String page = null;
            CommandDefiner commandDefiner = new CommandDefiner();
            Command command = commandDefiner.define(request);
            page = command.execute(request);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } catch (CommandException |  DaoException e) {
            LOGGER.error("Command failed", e);
            throw new ServiceException("Command failed", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAll();
        super.destroy();
    }
}
