package com.example.monitoringmanagementservice.services;

import com.example.monitoringmanagementservice.services.consumer.MessageDeviceDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface StoreDeviceService {
    void storeDevice(MessageDeviceDTO messageDeviceDTO) throws IOException;
}
