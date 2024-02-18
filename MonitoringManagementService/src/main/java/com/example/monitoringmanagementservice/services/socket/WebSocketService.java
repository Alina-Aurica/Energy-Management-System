package com.example.monitoringmanagementservice.services.socket;

import com.example.monitoringmanagementservice.entities.Device;
import com.example.monitoringmanagementservice.entities.Sensor;
import com.example.monitoringmanagementservice.repositories.DeviceRepository;
import com.example.monitoringmanagementservice.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        List<Sensor> sensors = sensorRepository.findAll();
        if (sensors != null) {
            for (Sensor sensor : sensors) {
                if (sensor.getTimestamp().after(Timestamp.valueOf(LocalDateTime.now().minusMinutes(1)))) {
                    System.out.println("Sensor - device ID " + sensor.getDeviceID());
                    Optional<Device> device = deviceRepository.findById(sensor.getDeviceID());
                    List<Device> deviceList = deviceRepository.findAll();
                    System.out.println("Lista device-uri " + deviceList);
                    if(device.isPresent()) {
                        if (sensor.getTotalHourlyConsumption() >= device.get().getMaximumHourlyEnergyConsumption()) {
                            String message = String.valueOf(device.get().getId());
                            simpMessagingTemplate.convertAndSend("/topic/websocket/client", message);
                        }
                    }
                }
            }
        }
    }
}
