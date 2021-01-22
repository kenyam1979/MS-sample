package com.kenyam.orderservice.reference;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {

    // Order queue routing
    public static final String ORDER_QUEUE = "order_queue";
    public static final String ORDER_ROUTING_KEY = "order_routing_key";
    // Shipment queue routing
    public static final String SHIPMENT_QUEUE = "shipment_queue";
    public static final String SHIPMENT_ROUTING_KEY = "shipment_routing_key";
    // Exchange
    public static final String TOPIC_EXCHANGE = "topic_exchange";

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE);
    }

    @Bean
    public Queue shipmentQueue() {
        return new Queue(SHIPMENT_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with(ORDER_ROUTING_KEY);
    }

    @Bean
    public Binding shipmentBinding(Queue shipmentQueue, TopicExchange exchange) {
        return BindingBuilder.bind(shipmentQueue).to(exchange).with(SHIPMENT_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }



}
