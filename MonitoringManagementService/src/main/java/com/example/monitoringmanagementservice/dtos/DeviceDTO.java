package com.example.monitoringmanagementservice.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class DeviceDTO {
    private UUID id;
    private String name;
    private String description;
    private String address;
    private Float maximumHourlyEnergyConsumption;
    private UUID clientID;
}
