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
@CrossOrigin(origins = "http://localhost:63342") // Allow frontend requests
public class ChatController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/sendMessage") // Listen for messages at "/app/sendMessage"
    @SendTo("/topic/message") // Broadcast messages to "/topic/message"
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        chatMessageService.saveMessage(message.getSender(), message.getContent()); // Save message in the database
        return message; // Send message to WebSocket subscribers
    }

    @GetMapping("/chat/messages") // API endpoint to get all chat messages
    public List<ChatMessage> getAllMessages() {
        return chatMessageService.getAllMessages();
    }
}
