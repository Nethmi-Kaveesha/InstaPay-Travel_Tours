package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.model.ChatMessage;
import com.example.InstaPay_Travel_Tours.repo.ChatMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage saveMessage(String sender, String content) {
        ChatMessage message = new ChatMessage();
        message.setSender(sender);
        message.setContent(content);
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }
}
