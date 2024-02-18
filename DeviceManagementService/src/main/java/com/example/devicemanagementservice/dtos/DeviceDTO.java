package com.example.devicemanagementservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDTO {
    @NonNull
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String address;
    @NonNull
    private Float maximumHourlyEnergyConsumption;
    private UUID clientID;
}
