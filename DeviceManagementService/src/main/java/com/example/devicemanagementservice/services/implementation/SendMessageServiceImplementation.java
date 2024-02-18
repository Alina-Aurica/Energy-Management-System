package com.example.devicemanagementservice.services.implementation;

import com.example.devicemanagementservice.dtos.MessageDeviceDTO;
import com.example.devicemanagementservice.entities.Device;
import com.example.devicemanagementservice.entities.Status;
import com.example.devicemanagementservice.services.SendMessageService;
import com.example.devicemanagementservice.services.publisher.RabbitMQJsonProducer;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImplementation implements SendMessageService {
    private RabbitMQJsonProducer rabbitMQJsonProducer;

    public SendMessageServiceImplementation(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @Override
    public void sendMessage(Device device, Status status){
        MessageDeviceDTO messageDeviceDTO = new MessageDeviceDTO();
        messageDeviceDTO.setId(device.getId());
        messageDeviceDTO.setName(device.getName());
        messageDeviceDTO.setDescription(device.getDescription());
        messageDeviceDTO.setAddress(device.getAddress());
        messageDeviceDTO.setMaximumHourlyEnergyConsumption(device.getMaximumHourlyEnergyConsumption());
        messageDeviceDTO.setClientID(device.getClientID());
        messageDeviceDTO.setStatus(status);

        rabbitMQJsonProducer.sendJsonMessage(messageDeviceDTO);
        rabbitMQJsonProducer.sendDSJsonMessage(messageDeviceDTO);
    }
}
