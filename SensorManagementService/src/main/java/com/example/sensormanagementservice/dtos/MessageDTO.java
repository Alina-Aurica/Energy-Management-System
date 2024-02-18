package com.example.sensormanagementservice.dtos;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class MessageDTO {
    private Timestamp timestamp;
    private UUID deviceID;
    private Float measurementValue;

}
