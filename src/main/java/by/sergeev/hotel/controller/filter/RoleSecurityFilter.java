package by.sergeev.hotel.controller.filter;


import by.sergeev.hotel.controller.command.*;
import by.sergeev.hotel.entity.SessionUser;
import by.sergeev.hotel.entity.enums.Role;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * The type Role security filter.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/controller"})
public class RoleSecurityFilter implements Filter {

    private static final int FORBIDDEN_ACCESS_ERROR_NUMBER = 403;
    private static final Logger LOGGER = LogManager.getLogger(RoleSecurityFilter.class);

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String commandLine = request.getParameter(PageParameter.COMMAND);
        Command command = CommandDefiner.define(request);
        Role userRole;
        if (Objects.isNull(session.getAttribute(PageParameter.SESSION_USER))) {
            userRole = Role.GUEST;
        } else {
            SessionUser sessionUser = (SessionUser) (session.getAttribute(PageParameter.SESSION_USER));
            userRole = sessionUser.getRole();
        }
        Set<CommandType> commands = new HashSet<>();
        switch (userRole) {
            case USER:
                commands = RolePermission.USER.getRoleCommands();
                break;
            case ADMIN:
                commands = RolePermission.ADMIN.getRoleCommands();
                break;
            case GUEST:
                commands = RolePermission.GUEST.getRoleCommands();
                break;
        }
        if (!commands.contains(CommandType.valueOf(commandLine.toUpperCase()))) {
            LOGGER.log(Level.ERROR, "Role {} has no access to {} command", userRole, command);
            response.sendError(FORBIDDEN_ACCESS_ERROR_NUMBER);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

