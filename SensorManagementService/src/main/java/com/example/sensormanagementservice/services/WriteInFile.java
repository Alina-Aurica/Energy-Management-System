package com.example.sensormanagementservice.services;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public interface WriteInFile {
    void writeInConfigFileWithAppend(UUID deviceID) throws IOException;
    void deleteFromConfigFile(UUID deviceID);
}

