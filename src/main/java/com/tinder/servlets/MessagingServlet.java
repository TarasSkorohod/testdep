package com.tinder.servlets;

import com.tinder.models.Message;
import com.tinder.models.User;
import com.tinder.services.MessageService;
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
import java.util.List;

@RequiredArgsConstructor
public class MessagingServlet extends HttpServlet {
    private final TemplateEngine engine = new TemplateEngine(this);
    private final MessageService messageService;
    private final UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        if (SessionRelated.find(req).isPresent() &&
                SessionRelated.findOrDie(req).getValue().equals(UserTracker.getCookie())) {
            User user = UserTracker.getUser();
            if (req.getPathInfo() != null) {
                int receiver = Integer.parseInt(req.getPathInfo().replace("/", ""));
                List<Message> messages = messageService.getAllMessagesByUsers(user.getId(), receiver);
                data.put("messages", messages);
                data.put("sender", user.getId());
                data.put("receiver", userService.getUser(receiver));

            } else resp.sendRedirect("/login");
            engine.render("chat.ftl", data, resp);

        } else resp.sendRedirect("/login");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int receiver = Integer.parseInt(req.getPathInfo().replace("/", ""));
        String content = req.getParameter("content").trim();
        User sender = UserTracker.getUser();
        if (!content.isEmpty())
            messageService.createNewMessage(new Message(sender.getId(), receiver, content));
        resp.sendRedirect("/messages" + req.getPathInfo());

    }
}
