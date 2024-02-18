package com.example.chatmanagementservice.service;

import com.example.chatmanagementservice.dto.ChatMessageDTO;
import com.example.chatmanagementservice.entity.ChatMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChatMessageService {
    ChatMessageDTO saveInDatabase(ChatMessage chatMessage);
    List<ChatMessageDTO> findAllConversation(ChatMessage chatMessage);

    ChatMessageDTO updateChatMessage(ChatMessage chatMessage);
}
