package com.example.devicemanagementservice.services;

import com.example.devicemanagementservice.entities.Device;
import com.example.devicemanagementservice.entities.Status;
import org.springframework.stereotype.Component;

@Component
public interface SendMessageService {
    void sendMessage(Device device, Status status);
}
