package com.tinder.servlets;

import com.tinder.models.Like;
import com.tinder.models.User;
import com.tinder.services.UserService;
import com.tinder.utils.SessionRelated;
import com.tinder.utils.TemplateEngine;
import com.tinder.utils.UserTracker;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RequiredArgsConstructor
public class UserServlet extends HttpServlet {
    TemplateEngine engine = new TemplateEngine(this);
    private final UserService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        if (SessionRelated.find(req).isPresent()
                && SessionRelated.findOrDie(req).getValue().equalsIgnoreCase(UserTracker.getCookie())) {
            User user = UserTracker.getUser();
            User selected = service.getUserToDisplay(user);
            if (selected != null) {
                data.put("image_url", selected.getUrl());
                data.put("firstname", selected.getFirstname());
                data.put("lastname", selected.getLastname());
                data.put("age", selected.getAge());
                engine.render("like-page.ftl", data, resp);
            } else resp.sendRedirect("/liked");
        } else resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button = req.getParameter("button");
        if (SessionRelated.find(req).isPresent()
                && SessionRelated.findOrDie(req).getValue().equalsIgnoreCase(UserTracker.getCookie())) {
            User user = UserTracker.getUser();
            User selected = service.getUserToDisplay(user);

            if (button.equals("dislike") && selected != null) {
                service.addLike(new Like(user.getId(), selected.getId(), false));
                resp.sendRedirect("/users");
            } else if (button.equals("like") && selected != null) {
                service.addLike(new Like(user.getId(), selected.getId(), true));
                resp.sendRedirect("/users");
            } else if (button.equals("likes")) {
                resp.sendRedirect("/liked");
            } else if (button.equals("logout")) {
                resp.sendRedirect("/logout");
            } else {
                resp.sendRedirect("/liked");
            }
        }
    }
}
