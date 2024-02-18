package com.example.devicemanagementservice.services.implementation;

import com.example.devicemanagementservice.dtos.DeviceDTO;
import com.example.devicemanagementservice.entities.Device;
import com.example.devicemanagementservice.entities.Status;
import com.example.devicemanagementservice.repositories.DeviceRepository;
import com.example.devicemanagementservice.services.DeviceService;
import com.example.devicemanagementservice.services.SendMessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceServiceImplementation implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SendMessageService sendMessageService;

    @Override
    public Optional<DeviceDTO> findById(UUID id) {
        Optional<Device> device = deviceRepository.findById(id);
        Optional<DeviceDTO> deviceDTO = Optional.ofNullable(modelMapper.map(device, DeviceDTO.class));
        return deviceDTO;
    }

    @Override
    public Optional<DeviceDTO> findByName(String name) {
        Optional<Device> device = deviceRepository.findByName(name);
        Optional<DeviceDTO> deviceDTO = Optional.ofNullable(modelMapper.map(device, DeviceDTO.class));
        return deviceDTO;
    }

    @Override
    public List<DeviceDTO> findAllDevice() {
        List<Device> devices = deviceRepository.findAll();
        List<DeviceDTO> deviceDTO = devices.stream().map(device -> modelMapper.map(device, DeviceDTO.class)).collect(Collectors.toList());
        return deviceDTO;
    }

    @Override
    public List<DeviceDTO> findAllDeviceByName(String name) {
        List<Device> devices = deviceRepository.findAllByName(name);
        List<DeviceDTO> deviceDTO = devices.stream().map(device -> modelMapper.map(device, DeviceDTO.class)).collect(Collectors.toList());
        return deviceDTO;
    }

    @Override
    public List<DeviceDTO> findAllDeviceByAddress(String address) {
        List<Device> devices = deviceRepository.findAllByAddress(address);
        List<DeviceDTO> deviceDTO = devices.stream().map(device -> modelMapper.map(device, DeviceDTO.class)).collect(Collectors.toList());
        return deviceDTO;
    }

    @Override
    public List<DeviceDTO> findAllDeviceByMaximumHourlyEnergyConsumption(Float maximumHourlyEnergyConsumption) {
        List<Device> devices = deviceRepository.findAllByMaximumHourlyEnergyConsumption(maximumHourlyEnergyConsumption);
        List<DeviceDTO> deviceDTO = devices.stream().map(device -> modelMapper.map(device, DeviceDTO.class)).collect(Collectors.toList());
        return deviceDTO;
    }

    @Override
    public List<DeviceDTO> findAllDeviceByClientID(UUID clientID) {
        List<Device> devices = deviceRepository.findAllByClientID(clientID);
        List<DeviceDTO> deviceDTO = devices.stream().map(device -> modelMapper.map(device, DeviceDTO.class)).collect(Collectors.toList());
        return deviceDTO;
    }

    @Override
    public Optional<DeviceDTO> insertDevice(Device device) {
        Device deviceInserted = deviceRepository.save(device);
        Optional<DeviceDTO> deviceDTO = Optional.ofNullable(modelMapper.map(deviceInserted, DeviceDTO.class));
        sendMessageService.sendMessage(deviceInserted, Status.ADD);
        return deviceDTO;
    }

    @Override
    public Optional<DeviceDTO> updateDeviceMaximumHourlyEnergyConsumption(String name, Float maximumHourlyEnergyConsumption) {
        Optional<Device> deviceForUpdate = deviceRepository.findByName(name);
        deviceForUpdate.get().setMaximumHourlyEnergyConsumption(maximumHourlyEnergyConsumption);
        deviceRepository.save(deviceForUpdate.get());
        Optional<DeviceDTO> deviceDTO = Optional.ofNullable(modelMapper.map(deviceForUpdate, DeviceDTO.class));
        sendMessageService.sendMessage(deviceForUpdate.get(), Status.UPDATE);
        return deviceDTO;
    }

    @Override
    public Optional<DeviceDTO> deleteDeviceByName(String name) {
        Optional<Device> device = deviceRepository.findByName(name);
        deviceRepository.delete(device.get());
        Optional<DeviceDTO> deviceDTO = Optional.ofNullable(modelMapper.map(device, DeviceDTO.class));
        sendMessageService.sendMessage(device.get(), Status.DELETE);
        return deviceDTO;
    }

    @Override
    public Optional<DeviceDTO> deleteDeviceByClientID(UUID clientID) {
        Optional<Device> device = deviceRepository.findByClientID(clientID);
        deviceRepository.delete(device.get());
        Optional<DeviceDTO> deviceDTO = Optional.ofNullable(modelMapper.map(device, DeviceDTO.class));
        sendMessageService.sendMessage(device.get(), Status.DELETE);
        return deviceDTO;
    }

    @Override
    public Optional<DeviceDTO> addClientID(String name, UUID clientID) {
        Optional<Device> device = deviceRepository.findByName(name);
        device.get().setClientID(clientID);
        deviceRepository.save(device.get());
        Optional<DeviceDTO> deviceDTO = Optional.ofNullable(modelMapper.map(device, DeviceDTO.class));
        sendMessageService.sendMessage(device.get(), Status.UPDATE);
        return deviceDTO;
    }

}
