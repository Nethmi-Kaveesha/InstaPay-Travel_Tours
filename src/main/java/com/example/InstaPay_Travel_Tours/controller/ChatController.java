package com.example.InstaPay_Travel_Tours.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:63342") // Allow frontend requests
public class ChatController {

    @MessageMapping("/sendMessage") // Listen for "/app/sendMessage"
    @SendTo("/topic/message") // Broadcast messages to "/topic/message"
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }

@GetMapping("chat")
    public String chat(){
        return "chat";
    }

}
