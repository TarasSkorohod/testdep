package com.tinder.services;

import com.tinder.dao.repositories.UserDao;
import com.tinder.models.User;
import com.tinder.enums.RegistrationMessageEnum;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RegistrationService {
    private final String defaultProfilePicture = "https://thumbs.dreamstime.com/b/user-icon-trendy-flat-style-isolated-grey-background-user-symbol-user-icon-trendy-flat-style-isolated-grey-background-123663211.jpg";

    private final UserDao userDao;

    public String registerUser(String email, String password, String passwordConf, String firstname, String lastname,
                               int age, String url) {
        List<User> users = userDao.getAll();
        if (users.stream().anyMatch(x -> x.getEmail().equals(email))) {
            return RegistrationMessageEnum.EMAIL_ALREADY_USED.getMessage();
        } else if (password.equals(passwordConf)) {
            if (url.isEmpty()) url = defaultProfilePicture;
            User user = new User(email, password, firstname, lastname, age, url);
            userDao.save(user);
            return RegistrationMessageEnum.SUCCESS.getMessage();
        }
        return RegistrationMessageEnum.FAIL.getMessage();
    }
}


