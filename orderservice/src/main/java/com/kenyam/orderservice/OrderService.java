package com.kenyam.orderservice;

import com.kenyam.orderservice.reference.MessagingConfig;
import com.kenyam.orderservice.reference.Shipment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Order getOrderById (int id) {
        try {
            return orderRepository.searchById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Order> getOrderAll () {
        try {
            return orderRepository.fetchAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createOrder (Order order) {
        // Create an order
        order.setStatus("INITIALIZED");
        orderRepository.save(order);

        // Push the order to a queue for shipping
        rabbitTemplate.convertAndSend(MessagingConfig.TOPIC_EXCHANGE, MessagingConfig.ORDER_ROUTING_KEY, order);

    }

    // Confirming shipment status and update order status
    public void confirmShipment (Shipment shipment) {
        int orderId = Integer.parseInt(shipment.getOrderId());
        Order order = this.getOrderById(orderId);

        switch (shipment.getStatus()) {
            case "COMPLETED":
                order.setStatus("DELIVERED");
                break;
            case "DISPATCHED":
                order.setStatus("IN TRANSIT");
                break;
            case "PROCESSING":
                order.setStatus("PROCESSING");
                break;
            default:
                System.out.println("Invalid shipment status");
        }

        orderRepository.update(orderId, order);

    }
}
