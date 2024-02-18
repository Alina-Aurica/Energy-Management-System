package com.example.chatmanagementservice.controller;

import com.example.chatmanagementservice.entity.ChatMessage;
import com.example.chatmanagementservice.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ChatController {
    private ChatMessageService chatMessageService;

    public ChatController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/topic/admin/{clientId}")
    //@SendTo("/topic/admin")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        //System.out.println("Mesaj: " + chatMessage.toString());
        this.chatMessageService.saveInDatabase(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/topic/seen/{clientID}")
    public ChatMessage sendSeenNotification(@Payload ChatMessage chatMessage){
        //System.out.println("Mesaj cu seen: " + chatMessage.toString());
        return chatMessage;
    }

    @MessageMapping("/topic/typing/admin/{clientID}")
    public String sendTypingNotificationAdmin(@Payload String typingNotificationMessage){
        //System.out.println("Typing admin...");
        return typingNotificationMessage;
    }

    @MessageMapping("/topic/typing/client/{clientID}")
    public String sendTypingNotificationClient(@Payload String typingNotificationMessage){
        //System.out.println("Typing client...");
        return typingNotificationMessage;
    }

}
