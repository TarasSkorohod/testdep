package com.tinder.services;

import com.tinder.dao.repositories.MessagesDao;
import com.tinder.models.Message;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MessageService {
    private final MessagesDao messagesDao;

    public List<Message> getAllMessages() {
        return messagesDao.getAll();
    }

    public void createNewMessage(Message message) {
        messagesDao.save(message);
    }

    public Message getMessage(int id) {
        return messagesDao.get(id).orElse(null);
    }

    public List<Message> getAllMessagesByUsers(int senderId, int receiverId) {
        return messagesDao.getAllMessagesByUsers(senderId, receiverId);
    }

    public void deleteMessage(int id) {
        messagesDao.delete(id);
    }
}
