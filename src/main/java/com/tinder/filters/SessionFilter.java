package com.tinder.filters;

import com.tinder.utils.SessionRelated;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter implements HttpFilter {
    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (SessionRelated.find(request).isEmpty()) response.sendRedirect("/login");
        else chain.doFilter(request, response);
    }
}
