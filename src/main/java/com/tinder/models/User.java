package com.tinder.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int age;
    private String url;

    public User(String email, String password, String firstname, String lastname, int age, String pictureUrl) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.url = pictureUrl;
    }
}
