package com.example.chatmanagementservice.repository;

import com.example.chatmanagementservice.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    ChatMessage findBySenderAndReceiverAndContent(UUID sender, UUID receiver, String content);
    ChatMessage findBySenderAndReceiverAndContentAndTimestamp(UUID sender, UUID receiver, String content, Timestamp timestamp);
    List<ChatMessage> findAllBySenderAndReceiver(UUID sender, UUID receiver);

}
