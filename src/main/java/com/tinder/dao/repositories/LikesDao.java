package com.tinder.dao.repositories;

import com.tinder.dao.Dao;
import com.tinder.models.Like;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LikesDao implements Dao<Like> {
    private final Connection connection;

    public LikesDao(Connection connection) {
        this.connection = connection;
    }

    private final String getLikes = "select * from likes";

    private final String getLike = "select * from likes where id = ?";

    private final String insertLike = "insert into likes(user_from, user_to, status)" +
            "values(?, ?, ?)";
    private final String deleteLike = "delete from likes where id = ?";

    @Override
    @SneakyThrows
    public List<Like> getAll() {
        List<Like> likes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(getLikes)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Like l = new Like(
                        resultSet.getInt("id"),
                        resultSet.getInt("userFrom"),
                        resultSet.getInt("userTo"),
                        resultSet.getBoolean("status")
                );
                likes.add(l);
            }
        }
        return likes;
    }

    @Override
    @SneakyThrows
    public Optional<Like> get(int id) {
        try (PreparedStatement statement = connection.prepareStatement(getLike)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next() ? Optional.empty() : Optional.of(
                    new Like(
                            resultSet.getInt("id"),
                            resultSet.getInt("userFrom"),
                            resultSet.getInt("userTo"),
                            resultSet.getBoolean("status")
                    ));
        }
    }
    @Override
    public List<Like> getBy(Predicate<Like> predicate) {
        return getAll().stream().filter(predicate).toList();
    }

    @Override
    @SneakyThrows
    public void save(Like entity) {
        try (PreparedStatement statement = connection.prepareStatement(insertLike)) {
            statement.setInt(1, entity.getUserFrom());
            statement.setInt(2, entity.getUserTo());
            statement.setBoolean(3, entity.isStatus());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(deleteLike)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }
}
