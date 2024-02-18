package com.example.monitoringmanagementservice.services.implementation;

import com.example.monitoringmanagementservice.dtos.MessageDTO;
import com.example.monitoringmanagementservice.entities.Sensor;
import com.example.monitoringmanagementservice.repositories.DeviceRepository;
import com.example.monitoringmanagementservice.repositories.SensorRepository;
import com.example.monitoringmanagementservice.services.StoreSensorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreSensorServiceImplementation implements StoreSensorService {
    public SensorRepository sensorRepository;
    public DeviceRepository deviceRepository;
    public ModelMapper modelMapper;

    public StoreSensorServiceImplementation(SensorRepository sensorRepository, DeviceRepository deviceRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.deviceRepository = deviceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void storeDataSensor(MessageDTO messageDTO){
        List<Sensor> sensors = sensorRepository.findAllByDeviceID(messageDTO.getDeviceID());
        for(Sensor sensor: sensors) {
            if(sensor.getTimestamp().after(Timestamp.valueOf(LocalDateTime.now().minusMinutes(1)))) {
                sensor.setTotalHourlyConsumption(sensor.getTotalHourlyConsumption() + messageDTO.getMeasurementValue());
                sensorRepository.save(sensor);
                return ;
            }
        }

        Sensor sensor = new Sensor();
        sensor.setTimestamp(messageDTO.getTimestamp());
        sensor.setDeviceID(messageDTO.getDeviceID());
        sensor.setTotalHourlyConsumption(messageDTO.getMeasurementValue());
        sensorRepository.save(sensor);
    }

}
