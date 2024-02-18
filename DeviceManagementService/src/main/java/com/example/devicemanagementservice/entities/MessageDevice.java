package com.example.devicemanagementservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDevice implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private String address;
    private Float maximumHourlyEnergyConsumption;
    private UUID clientID;
    private Status status;
}
