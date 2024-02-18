package com.example.chatmanagementservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private UUID sender;
    private UUID receiver;
    private Timestamp timestamp;
    private boolean seen;

    public ChatMessage() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
