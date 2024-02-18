package com.example.monitoringmanagementservice.services.consumer;

import com.example.monitoringmanagementservice.dtos.MessageDTO;
import com.example.monitoringmanagementservice.services.StoreDeviceService;
import com.example.monitoringmanagementservice.services.StoreSensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    public StoreSensorService storeService;
    public StoreDeviceService storeDevice;


    public RabbitMQJsonConsumer(StoreSensorService storeSensorService, StoreDeviceService storeDeviceService) {
        this.storeService = storeSensorService;
        this.storeDevice = storeDeviceService;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJson(MessageDTO messageDTO){
        storeService.storeDataSensor(messageDTO);
        LOGGER.info(String.format("Received sensor message -> %s", messageDTO.toString()));
    }

    @RabbitListener(queues = {"${rabbitmq.queue.device.json.name}"})
    public void consumeJsonDevice(MessageDeviceDTO messageDeviceDTO) throws IOException {
        storeDevice.storeDevice(messageDeviceDTO);
        LOGGER.info(String.format("Received device message -> %s", messageDeviceDTO.toString()));
    }

}
