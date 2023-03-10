package com.tinder.services;

import com.tinder.dao.repositories.LikesDao;
import com.tinder.dao.repositories.UserDao;
import com.tinder.models.Like;
import com.tinder.models.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LikeService {
    private final UserDao userDao;
    private final LikesDao likesDao;
    private final Connection connection;

    public List<Like> getAllUsers() {
        return likesDao.getAll();
    }

    public void insertLike(Like like) {
        likesDao.save(like);
    }

    public Like getLike(int id) {
        return likesDao.get(id).orElse(null);
    }

    public void deleteLike(int id) {
        likesDao.delete(id);
    }

    public List<User> getLikedUser(int id) {
        return getLikedUserSQL(id);
    }

    @SneakyThrows
    private List<User> getLikedUserSQL(int id) {

        List<User> users = new ArrayList<>();
        String query = "select  *  from users where id  " +
                "in(select user_to from likes where user_from = ? and status = true)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int uid = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                String url = resultSet.getString("url");

                users.add(new User(uid, email, password, firstname, lastname, age, url));
            }
        }
        return users;
    }

}
