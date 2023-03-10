package com.tinder.dao.repositories;

import com.tinder.dao.Dao;
import com.tinder.models.User;
import lombok.SneakyThrows;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserDao implements Dao<User> {

    private final Connection connection;

    private final String getUsers = "select * from users";
    private final String getUser = "select * from users where id = ?";
    private final String insertUser = "insert into users(email, password, firstname, lastname, age, url)" +
            "values(?, ?, ? ,?, ?, ?)";
    private final String deleteUser = "delete from users where id = ?";

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(getUsers)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User u = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("url")
                );
                users.add(u);
            }
        }
        return users;
    }

    @SneakyThrows
    public List<User> getAllUsersToDisplay(int id) {
        List<User> users = new ArrayList<>();
        String query =
                "select distinct u.* from users u where u.id not in(select user_to from likes where user_from = ? ) and u.id != ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("url")
                ));
            }
            return users;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    @Override
    public List<User> getBy(Predicate<User> predicate) {
        return getAll().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public Optional<User> get(int id) {

        try (PreparedStatement statement = connection.prepareStatement(getUser)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next() ? Optional.empty() : Optional.of(
                    new User(
                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getInt("age"),
                            resultSet.getString("url")
                    )
            );
        }
    }

    @Override
    @SneakyThrows
    public void save(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(insertUser)) {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getFirstname());
            statement.setString(4, entity.getLastname());
            statement.setInt(5, entity.getAge());
            statement.setString(6, entity.getUrl());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(deleteUser)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }
}
