package com.example.chatmanagementservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ChatMessageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private UUID sender;
    private UUID receiver;
    private Timestamp timestamp;
    private boolean seen;
}
