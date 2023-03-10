package com.tinder.servlets;


import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RequiredArgsConstructor
public class StaticFileServlet extends HttpServlet {

    private final String directory;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get(directory, req.getPathInfo());
        ServletOutputStream os = resp.getOutputStream();
        Files.copy(path, os);
    }
}
