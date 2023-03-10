package com.tinder.utils;

import com.tinder.models.User;
public class UserTracker {
    private static String cookie;
    private static User user;

    public static void setCookie(String cookie) {
        UserTracker.cookie = cookie;
    }

    public static void setUser(User user) {
        UserTracker.user = user;
    }

    public static String getCookie() {
        return cookie;
    }

    public static User getUser() {
        return user;
    }
}

