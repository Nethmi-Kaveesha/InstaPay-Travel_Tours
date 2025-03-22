package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.model.ChatMessage;
;
import com.example.InstaPay_Travel_Tours.repo.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void saveMessage(String sender, String content) {
        ChatMessage message = new ChatMessage(sender, content);
        chatMessageRepository.save(message);
    }

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }
}
