package com.example.InstaPay_Travel_Tours.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Mark this class as a JPA entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;

    private String sender;
    private String content;

    @Column(nullable = false) // Ensure that the timestamp is always set
    private LocalDateTime timestamp; // Store the timestamp of when the message was sent

    // Constructor with timestamp
    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now(); // Automatically set the timestamp when the message is created
    }
}
