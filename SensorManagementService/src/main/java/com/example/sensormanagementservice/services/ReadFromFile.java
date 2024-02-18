package com.example.sensormanagementservice.services;

import com.example.sensormanagementservice.dtos.MessageDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ReadFromFile {
    List<UUID> readFromTextFileDeviceID();
    void readFromCSVFile();
}
