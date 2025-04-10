package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.model.ChatMessage;
import com.example.InstaPay_Travel_Tours.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class ChatController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/message")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        chatMessageService.saveMessage(message.getSender(), message.getContent());
        return message;
    }

    @GetMapping("/chat/messages")
    public List<ChatMessage> getAllMessages() {
        return chatMessageService.getAllMessages();
    }
}
