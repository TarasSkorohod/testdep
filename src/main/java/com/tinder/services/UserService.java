package com.tinder.services;

import com.tinder.dao.repositories.LikesDao;
import com.tinder.dao.repositories.UserDao;
import com.tinder.models.Like;
import com.tinder.models.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final LikesDao likesDao;

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUserToDisplay(User user) {
        List<User> users = userDao.getAllUsersToDisplay(user.getId());
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public User getUser(int id) {
        return userDao.get(id).orElse(null);
    }

    public void insertUser(User user) {
        userDao.save(user);
    }

    public void addLike(Like like) {
        likesDao.save(like);
    }

    public void deleteUser(int id) {
        userDao.delete(id);
    }
}
