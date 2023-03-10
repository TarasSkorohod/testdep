package com.tinder.services;

import com.tinder.dao.repositories.UserDao;
import com.tinder.models.User;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LoginService {
    private final UserDao userDao;

    public boolean isUserAuth(String email, String password) {
        List<User> users = userDao.getAll();
        return users.stream()
                .anyMatch(x -> x.getEmail().equals(email) && x.getPassword().equals(password));
    }

    public User getUser(String email, String password) {
        Optional<User> find = userDao.getAll().stream()
                .filter(x -> x.getEmail().equals(email) && x.getPassword().equals(password))
                .findFirst();
        return find.orElse(null);
    }

}
