package com.example.monitoringmanagementservice.services.implementation;

import com.example.monitoringmanagementservice.services.consumer.MessageDeviceDTO;
import com.example.monitoringmanagementservice.entities.Device;
import com.example.monitoringmanagementservice.entities.Status;
import com.example.monitoringmanagementservice.repositories.DeviceRepository;
import com.example.monitoringmanagementservice.services.StoreDeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StoreDeviceServiceImplementation implements StoreDeviceService {

    public DeviceRepository deviceRepository;
    public ModelMapper modelMapper;

    public StoreDeviceServiceImplementation(DeviceRepository deviceRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void storeDevice(MessageDeviceDTO messageDeviceDTO) throws IOException {
        System.out.println("Suntem aici!");
        if(messageDeviceDTO.getStatus() == Status.ADD || messageDeviceDTO.getStatus() == Status.UPDATE) {
            Device device = modelMapper.map(messageDeviceDTO, Device.class);
            deviceRepository.save(device);
        } else {
            Device device = modelMapper.map(messageDeviceDTO, Device.class);
            deviceRepository.delete(device);
        }
    }
}
