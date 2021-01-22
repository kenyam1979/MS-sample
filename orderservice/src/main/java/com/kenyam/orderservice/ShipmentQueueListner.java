package com.kenyam.orderservice;

import com.kenyam.orderservice.reference.MessagingConfig;
import com.kenyam.orderservice.reference.Shipment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentQueueListner {

    @Autowired
    OrderService orderService;

    // This listens to shipment status
    @RabbitListener(queues = MessagingConfig.SHIPMENT_QUEUE)
    public void consumeMessageFromQueue (Shipment shipment) {
        System.out.println("Message received from queue: " + shipment);

        // Update order status based on shipment
        orderService.confirmShipment(shipment);
    }

}

