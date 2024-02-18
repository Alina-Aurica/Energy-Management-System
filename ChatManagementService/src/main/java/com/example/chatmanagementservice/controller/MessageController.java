package com.example.chatmanagementservice.controller;

import com.example.chatmanagementservice.entity.ChatMessage;
import com.example.chatmanagementservice.service.ChatMessageService;
import com.example.chatmanagementservice.service.implementation.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chatMessage")
public class MessageController {
    private ChatMessageService chatMessageService;
    private JwtService jwtService;

    public  MessageController(ChatMessageService chatMessageService, JwtService jwtService) {
        this.chatMessageService = chatMessageService;
        this.jwtService = jwtService;
    }

    @GetMapping("/findAllMessages/{sender}/{receiver}")
    public ResponseEntity getAllConversation(@RequestHeader(value="Authorization", required=false) String token, @PathVariable UUID sender, @PathVariable UUID receiver){
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidAndActive(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);
        chatMessage.setReceiver(receiver);
        return ResponseEntity.status(HttpStatus.OK).body(this.chatMessageService.findAllConversation(chatMessage));
    }

    @PutMapping("/updateMessage")
    public ResponseEntity updateChatMessage(@RequestHeader(value="Authorization", required=false) String token, @RequestBody ChatMessage chatMessage){
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidAndActive(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.chatMessageService.updateChatMessage(chatMessage));
    }
}
