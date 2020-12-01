package by.sergeev.hotel.controller.filter;

import by.sergeev.hotel.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The type Page security filter.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class PageSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + PagePath.INDEX);
    }

    @Override
    public void destroy() {
    }
}
