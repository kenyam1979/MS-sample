package com.kenyam.shipmentservice;

import com.kenyam.shipmentservice.reference.MessagingConfig;
import com.kenyam.shipmentservice.reference.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Shipment getShipmentById (int id) {
        return shipmentRepository.searchById(id);
    }

    public Shipment getShipmentByOrderId (int id) {
        return shipmentRepository.searchByOrderId(id);
    }

    public List<Shipment> getShipmentAll () {
        return shipmentRepository.fetchAll();
    }

    public void createShipment(Order order) {
        if (order == null) {
            return;
        }
        // Convert an order to a shipment
        Shipment shipment = new Shipment();
        shipment.setOrderId(order.getId());
        shipment.setStatus("PROCESSING");

        try {
            // Save the shipment
            shipmentRepository.save(shipment);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Push the shipment to a queue for order status update
        rabbitTemplate.convertAndSend(MessagingConfig.TOPIC_EXCHANGE, MessagingConfig.SHIPMENT_ROUTING_KEY, shipment);

    }

    public Shipment dispatchShipment (int id) {
        Shipment shipment;
        try {
            shipment = this.getShipmentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Change the status of a shipment
        shipment.setShippingAddress("1-2-3 Higashi Nakano, Nakano, Tokyo");
        shipment.setShippingDate("2020-12-31");
        shipment.setStatus("DISPATCHED");

        try {
            // Save the shipment
            shipmentRepository.update(id, shipment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Push the shipment to a queue for order status update
        rabbitTemplate.convertAndSend(MessagingConfig.TOPIC_EXCHANGE, MessagingConfig.SHIPMENT_ROUTING_KEY, shipment);
        return shipment;
    }

    public Shipment completeShipment (int id) {
        Shipment shipment;
        try {
            shipment = this.getShipmentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Change the status of a shipment
        shipment.setStatus("COMPLETED");

        try {
            // Save the shipment
            shipmentRepository.update(id, shipment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Push the shipment to a queue for order status update
        rabbitTemplate.convertAndSend(MessagingConfig.TOPIC_EXCHANGE, MessagingConfig.SHIPMENT_ROUTING_KEY, shipment);
        return shipment;
    }

}
