package com.example.monitoringmanagementservice.repositories;

import com.example.monitoringmanagementservice.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    List<Sensor> findAllByDeviceID(UUID deviceID);



}
