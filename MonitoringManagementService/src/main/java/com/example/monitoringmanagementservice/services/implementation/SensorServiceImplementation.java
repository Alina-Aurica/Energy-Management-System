package com.example.monitoringmanagementservice.services.implementation;

import com.example.monitoringmanagementservice.dtos.SensorDTO;
import com.example.monitoringmanagementservice.entities.Sensor;
import com.example.monitoringmanagementservice.repositories.SensorRepository;
import com.example.monitoringmanagementservice.services.SensorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SensorServiceImplementation implements SensorService {

    public SensorRepository sensorRepository;
    public ModelMapper modelMapper;

    public SensorServiceImplementation(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SensorDTO> findAllByDeviceIDAndTimestamp(UUID deviceID, Timestamp timestamp){
        List<Sensor> sensors = sensorRepository.findAllByDeviceID(deviceID);
        List<SensorDTO> sensorsDTO = new ArrayList<SensorDTO>();
        for(Sensor sensor: sensors){
            if(sensor.getTimestamp().toLocalDateTime().toLocalDate().equals(timestamp.toLocalDateTime().toLocalDate())){
                SensorDTO sensorDTO = modelMapper.map(sensor, SensorDTO.class);
                sensorsDTO.add(sensorDTO);
            }
        }
        return sensorsDTO;
    }
}
