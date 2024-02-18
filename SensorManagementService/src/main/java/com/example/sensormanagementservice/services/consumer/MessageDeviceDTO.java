package com.example.sensormanagementservice.services.consumer;

import com.example.sensormanagementservice.entities.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class MessageDeviceDTO {
    private UUID id;
    private String name;
    private String description;
    private String address;
    private Float maximumHourlyEnergyConsumption;
    private UUID clientID;
    private Status status;
}
