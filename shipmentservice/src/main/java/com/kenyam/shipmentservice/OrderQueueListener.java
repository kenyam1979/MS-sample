package com.kenyam.shipmentservice;

import com.kenyam.shipmentservice.reference.MessagingConfig;
import com.kenyam.shipmentservice.reference.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderQueueListener {

    @Autowired
    private ShipmentService shipmentService;

    @RabbitListener(queues = MessagingConfig.ORDER_QUEUE)
    public void consumeMessageFromQueue (Order order) {
        System.out.println("Message received from queue: " + order);

        // Create a shipment doc
        shipmentService.createShipment(order);
    }

}
