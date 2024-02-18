package com.example.devicemanagementservice.services;

import com.example.devicemanagementservice.dtos.DeviceDTO;
import com.example.devicemanagementservice.entities.Device;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface DeviceService {
    Optional<DeviceDTO> findById(UUID id);
    Optional<DeviceDTO> findByName(String name);
    List<DeviceDTO> findAllDevice();
    List<DeviceDTO> findAllDeviceByName(String name);
    List<DeviceDTO> findAllDeviceByAddress(String address);
    List<DeviceDTO> findAllDeviceByMaximumHourlyEnergyConsumption(Float maximumHourlyEnergyConsumption);
    List<DeviceDTO> findAllDeviceByClientID(UUID clientID);
    Optional<DeviceDTO> insertDevice(Device device);
    Optional<DeviceDTO> updateDeviceMaximumHourlyEnergyConsumption(String name, Float maximumHourlyEnergyConsumption);
    Optional<DeviceDTO> deleteDeviceByName(String name);
    Optional<DeviceDTO> deleteDeviceByClientID(UUID clientID);
    Optional<DeviceDTO> addClientID(String name, UUID clientID);
}
