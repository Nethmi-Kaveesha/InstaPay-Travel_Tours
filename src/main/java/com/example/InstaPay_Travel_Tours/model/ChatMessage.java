package com.example.InstaPay_Travel_Tours.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    // Ensure timestamp is automatically set when saving a new message
    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
