package com.tinder.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private int id;
    private int sender;
    private int receiver;
    private String content;
    private String sendDate;

    public Message(int sender, int receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Message(int sender, int receiver, String content, String sendDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sendDate = sendDate;
    }

    public Message(int id, int sender, int receiver, String content, String sendDate) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sendDate = sendDate;
    }
}
