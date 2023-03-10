package com.tinder.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    public static final String url = "jdbc:postgresql://localhost:5432/postgres";
    public static final String user = "tinder";
    public static final String password = "tinder";

    @SneakyThrows
    public Connection connection() {
        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static Connection connectionFromUrl(String jdbc_url){
        return DriverManager.getConnection(jdbc_url);
    }

    public void showErrorMessage(SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
        System.out.println("Error code: " + ex.getErrorCode());
    }
}
