package com.example.monitoringmanagementservice.services;

import com.example.monitoringmanagementservice.dtos.MessageDTO;
import org.springframework.stereotype.Component;

@Component
public interface StoreSensorService {
    void storeDataSensor(MessageDTO messageDTO);
}
