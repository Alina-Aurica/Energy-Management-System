package com.example.monitoringmanagementservice.dtos;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class SensorDTO {
    private UUID id;
    private Timestamp timestamp;
    private UUID deviceID;
    private Float totalHourlyConsumption;

}
