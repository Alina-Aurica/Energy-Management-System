package com.example.sensormanagementservice.services.consumer;

import com.example.sensormanagementservice.entities.Status;
import com.example.sensormanagementservice.services.WriteInFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
    private WriteInFile writeInFile;

    public RabbitMQJsonConsumer(WriteInFile writeInFile) {
        this.writeInFile = writeInFile;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.ds.json.name}"})
    public void consumeJsonDevice(MessageDeviceDTO messageDeviceDTO) throws IOException {
        if(messageDeviceDTO.getStatus() == Status.ADD) {
            writeInFile.writeInConfigFileWithAppend(messageDeviceDTO.getId());
        }

        if(messageDeviceDTO.getStatus() == Status.DELETE){
            writeInFile.deleteFromConfigFile(messageDeviceDTO.getId());
        }

        LOGGER.info(String.format("Received device message -> %s", messageDeviceDTO.toString()));
    }
}
