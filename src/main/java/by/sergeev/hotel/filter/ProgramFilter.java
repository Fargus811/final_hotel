package by.sergeev.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProgramFilter implements Filter {

    private static final String LANGUAGE_PARAMETER = "lang";
    private static final String REFERER = "referer";
    private static final String LOCALE = "LOCALE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.setAttribute("OriginURL", req.getRequestURL().toString());
        req.setAttribute("OriginURI", req.getRequestURL().toString());
        filterChain.doFilter(req,servletResponse);
    }

    @Override
    public void destroy() {
    }
}

