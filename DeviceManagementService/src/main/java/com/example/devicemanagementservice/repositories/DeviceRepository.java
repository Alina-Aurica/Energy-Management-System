package com.example.devicemanagementservice.repositories;

import com.example.devicemanagementservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Optional<Device> findByName(String name);
    Optional<Device> findByClientID(UUID clientID);
    List<Device> findAll();
    List<Device> findAllByName(String name);
    List<Device> findAllByAddress(String address);
    List<Device> findAllByMaximumHourlyEnergyConsumption(Float maximumHourlyEnergyConsumption);
    List<Device> findAllByClientID(UUID clientID);

}
