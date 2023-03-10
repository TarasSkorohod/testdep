package com.tinder.filters;

import com.tinder.enums.LoginMessageEnum;
import com.tinder.services.LoginService;
import com.tinder.utils.TemplateEngine;
import lombok.RequiredArgsConstructor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RequiredArgsConstructor
public class LoginFilter implements HttpFilter {

    private final TemplateEngine engine = new TemplateEngine(this);
    private final LoginService service;

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            HashMap<String, Object> data = new HashMap<>();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (service.isUserAuth(email, password)) chain.doFilter(request, response);
            else {
                data.put("message", LoginMessageEnum.LOGIN_FAIL.getMessage());
                engine.render("login.ftl", data, response);
            }
        } else chain.doFilter(request, response);
    }
}
