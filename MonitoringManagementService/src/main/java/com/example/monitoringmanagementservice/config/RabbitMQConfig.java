package com.example.monitoringmanagementservice.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.queue.device.json.name}")
    private String deviceJsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String sensorExchange;

    @Value("${rabbitmq.exchange.device.name}")
    private String deviceExchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    @Value("${rabbitmq.routing.device.json.key}")
    private String deviceRoutingJsonKey;

    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    @Bean
    public Queue deviceJsonQueue(){
        return new Queue(deviceJsonQueue);
    }

    @Bean
    public TopicExchange sensorExchange(){
        return new TopicExchange(sensorExchange);
    }
    @Bean
    public TopicExchange deviceExchange(){
        return new TopicExchange(deviceExchange);
    }

    // binding between Queue and TopicExchange using routing key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(sensorExchange())
                .with(routingJsonKey);
    }

    @Bean
    public Binding deviceJsonBinding(){
        return BindingBuilder
                .bind(deviceJsonQueue())
                .to(deviceExchange())
                .with(deviceRoutingJsonKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
