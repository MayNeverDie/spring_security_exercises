package ru.esphere.school.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class OpenAmCookieFilter implements Filter {

    private final String cookieName;

    public OpenAmCookieFilter(String cookieName) {
        this.cookieName = cookieName;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Initializing OpenAmCookieFilter!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final Cookie[] cookies = request.getCookies();

        if (Objects.isNull(cookies)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String openAmCookie = null;
        for (Cookie c : cookies) {
            String name = c.getName();
            if (name.equalsIgnoreCase(cookieName)) {
                openAmCookie = c.getValue();
                break;
            }
        }
        if (openAmCookie == null) {
            log.error("OpenamCookieFilter: cookie not found!");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
