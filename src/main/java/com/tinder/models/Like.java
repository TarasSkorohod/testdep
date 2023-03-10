package com.tinder.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Like {

    private int id;
    private int userFrom;
    private int userTo;
    private boolean status;

    public Like(int from, int to, boolean status) {
        this.userFrom = from;
        this.userTo = to;
        this.status = status;
    }
}
