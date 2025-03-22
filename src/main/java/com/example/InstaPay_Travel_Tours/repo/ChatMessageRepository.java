package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
