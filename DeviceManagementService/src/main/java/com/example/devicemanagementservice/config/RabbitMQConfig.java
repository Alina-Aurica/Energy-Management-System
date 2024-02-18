package com.example.devicemanagementservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.device.json.name}")
    private String deviceJsonQueue;

    @Value("${rabbitmq.exchange.device.name}")
    private String exchange;

    @Value("${rabbitmq.routing.device.json.key}")
    private String deviceRoutingJsonKey;

    @Value("${rabbitmq.queue.ds.json.name}")
    private String dsJsonQueue;

    @Value("${rabbitmq.exchange.ds.name}")
    private String dsExchange;

    @Value("${rabbitmq.routing.ds.json.key}")
    private String dsRoutingJsonKey;

    @Bean
    public Queue deviceJsonQueue(){
        return new Queue(deviceJsonQueue);
    }

    @Bean
    public Queue dsJsonQueue(){
        return new Queue(dsJsonQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public TopicExchange dsExchange(){
        return new TopicExchange(dsExchange);
    }

    // binding between Queue and TopicExchange using routing key
    @Bean
    public Binding deviceJsonBinding(){
        return BindingBuilder
                .bind(deviceJsonQueue())
                .to(exchange())
                .with(deviceRoutingJsonKey);
    }

    @Bean
    public Binding dsJsonBinding(){
        return BindingBuilder
                .bind(dsJsonQueue())
                .to(dsExchange())
                .with(dsRoutingJsonKey);
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
