package com.tinder;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.tinder.dao.repositories.LikesDao;
import com.tinder.dao.repositories.MessagesDao;
import com.tinder.dao.repositories.UserDao;
import com.tinder.database.DbHelper;
import com.tinder.filters.CookieFilter;
import com.tinder.filters.LoginFilter;
import com.tinder.filters.MessagingFilter;
import com.tinder.filters.SessionFilter;
import com.tinder.services.*;
import com.tinder.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) throws Exception {

        //to run on localhost
        Server server = new Server(5456);
        DbHelper helper = new DbHelper();
        Connection connection = helper.connection();



        ServletContextHandler handler = new ServletContextHandler();



        LikesDao likesDao = new LikesDao(connection);
        UserDao userDao = new UserDao(connection);
        MessagesDao messagesDao = new MessagesDao(connection);

        LoginService loginService = new LoginService(userDao);
        LikeService likeService = new LikeService(userDao, likesDao, connection);
        UserService userService = new UserService(userDao, likesDao);
        RegistrationService registrationService = new RegistrationService(userDao);
        MessageService messageService = new MessageService(messagesDao);

        RegistrationServlet registrationServlet = new RegistrationServlet(registrationService,loginService);
        LoginServlet loginServlet = new LoginServlet(loginService);
        LoginFilter loginFilter = new LoginFilter(loginService);
        UserServlet userServlet = new UserServlet(userService);
        LikeServlet likeServlet = new LikeServlet(likeService);
        MessagingServlet messagingServlet = new MessagingServlet(messageService, userService);

        handler.addServlet(RootServlet.class, "");
        handler.addServlet(new ServletHolder(registrationServlet), "/register");
        handler.addServlet(new ServletHolder(loginServlet), "/login");
        handler.addServlet(new ServletHolder(userServlet), "/users");
        handler.addServlet(LogoutServlet.class, "/logout");
        handler.addServlet(new ServletHolder(likeServlet), "/liked");
        handler.addServlet(new ServletHolder(messagingServlet), "/messages/*");
        handler.addServlet(new ServletHolder(new StaticFileServlet("src/main/resources/templates")), "/static/*");

        EnumSet<DispatcherType> dt = EnumSet.of(DispatcherType.REQUEST);

        handler.addFilter(CookieFilter.class, "", dt);
        handler.addFilter(CookieFilter.class, "/logout", dt);
        handler.addFilter(new FilterHolder(loginFilter), "/login/*", dt);
        handler.addFilter(SessionFilter.class, "/users", dt);
        handler.addFilter(SessionFilter.class, "/liked", dt);
        handler.addFilter(SessionFilter.class, "/messages/{id}", dt);
        handler.addFilter(MessagingFilter.class, "/messages/{id}", dt);

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
