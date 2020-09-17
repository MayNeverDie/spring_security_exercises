package ru.esphere.school.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class OpenAmCookieFilter extends OncePerRequestFilter {

    private final String cookieName;

    public OpenAmCookieFilter(String cookieName) {
        this.cookieName = cookieName;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
        filterChain.doFilter(request, response);
    }

}
