package com.tinder.servlets;

import com.tinder.models.User;
import com.tinder.services.LikeService;
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
import java.util.List;

@RequiredArgsConstructor
public class LikeServlet extends HttpServlet {
    private final TemplateEngine engine = new TemplateEngine(this);
    private final LikeService likeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionRelated.find(req).isPresent()) {
            HashMap<String, Object> data = new HashMap<>();
            User user = UserTracker.getUser();
            if (user != null) {
                List<User> likedUser = likeService.getLikedUser(user.getId());
                data.put("users", likedUser);
                engine.render("people-list.ftl", data, resp);
            } else resp.sendRedirect("/login");

        } else resp.sendRedirect("/login");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button = req.getParameter("button");
        if (button.equalsIgnoreCase("logout")) {
            resp.sendRedirect("/logout");
        }

    }
}
