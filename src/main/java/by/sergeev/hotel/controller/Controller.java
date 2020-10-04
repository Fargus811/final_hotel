package by.sergeev.hotel.controller;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.command.definer.CommandDefiner;
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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello</h3>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            tryProcessRequest(request, response);
        } catch (CommandException e) {
            LOGGER.error("Command failed", e);
        }
    }

    private void tryProcessRequest(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        String page = null;
        CommandDefiner commandDefiner = new CommandDefiner();
        Command command = commandDefiner.define(request);
        page = command.execute(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAll();
        super.destroy();
    }
}
