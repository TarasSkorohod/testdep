package com.tinder.servlets;

import com.tinder.models.User;
import com.tinder.services.LoginService;
import com.tinder.utils.SessionRelated;
import com.tinder.utils.TemplateEngine;
import com.tinder.utils.UserTracker;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@RequiredArgsConstructor
public class LoginServlet extends HttpServlet {
    private final TemplateEngine engine = new TemplateEngine(this);
    private final LoginService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        Cookie cookie = SessionRelated.newRandom();
        resp.addCookie(cookie);
        engine.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = service.getUser(req.getParameter("email"), req.getParameter("password"));
        String cookie = SessionRelated.findOrDie(req).getValue();
        UserTracker.setCookie(cookie);
        UserTracker.setUser(user);
        resp.sendRedirect("/users");
    }
}
