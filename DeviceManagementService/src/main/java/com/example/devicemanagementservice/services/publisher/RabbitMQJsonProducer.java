package com.example.devicemanagementservice.services.publisher;

import com.example.devicemanagementservice.dtos.MessageDeviceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.exchange.device.name}")
    private String exchange;

    @Value("${rabbitmq.routing.device.json.key}")
    private String deviceRoutingJsonKey;

    @Value("${rabbitmq.exchange.ds.name}")
    private String dsExchange;

    @Value("${rabbitmq.routing.ds.json.key}")
    private String dsRoutingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(MessageDeviceDTO messageDeviceDTO){
        LOGGER.info(String.format("Device message sent -> %s", messageDeviceDTO.toString()));
        rabbitTemplate.convertAndSend(exchange, deviceRoutingJsonKey, messageDeviceDTO);
    }

    public void sendDSJsonMessage(MessageDeviceDTO messageDeviceDTO){
        LOGGER.info(String.format("Ds message sent -> %s", messageDeviceDTO.toString()));
        rabbitTemplate.convertAndSend(dsExchange, dsRoutingJsonKey, messageDeviceDTO);
    }
}
