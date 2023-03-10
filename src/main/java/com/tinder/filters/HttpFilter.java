package com.tinder.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpFilter extends Filter {

    private boolean isHttp(ServletRequest request, ServletResponse response) {
        return request instanceof HttpServletRequest && response instanceof HttpServletResponse;
    }

    @Override
    default void init(FilterConfig filterConfig) throws ServletException {

    }

    void doHttpFilter(HttpServletRequest request,
                      HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    default void doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain) throws IOException, ServletException {
        if (isHttp(request, response)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            doHttpFilter(httpServletRequest, httpServletResponse, chain);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    default void destroy() {

    }
}
