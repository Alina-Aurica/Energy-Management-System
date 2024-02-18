package com.example.monitoringmanagementservice.services.consumer;

import com.example.monitoringmanagementservice.entities.Status;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
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
