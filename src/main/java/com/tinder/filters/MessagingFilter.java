package com.tinder.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessagingFilter implements HttpFilter {
    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            if (request.getPathInfo().equalsIgnoreCase("/")) response.sendRedirect("/list/");
            else chain.doFilter(request, response);
        } else chain.doFilter(request, response);
    }
}
