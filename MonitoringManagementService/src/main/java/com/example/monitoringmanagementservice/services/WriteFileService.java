package com.example.monitoringmanagementservice.services;

import com.example.monitoringmanagementservice.entities.Device;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface WriteFileService {
    void writeInConfigFileWithAppend(Device device) throws IOException;
    void deleteFromConfigFile(Device device);
}
