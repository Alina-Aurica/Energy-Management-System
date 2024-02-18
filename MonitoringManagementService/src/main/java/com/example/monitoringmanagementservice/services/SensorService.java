package com.example.monitoringmanagementservice.services;

import com.example.monitoringmanagementservice.dtos.SensorDTO;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Component
public interface SensorService {
    List<SensorDTO> findAllByDeviceIDAndTimestamp(UUID deviceID, Timestamp timestamp);
}
