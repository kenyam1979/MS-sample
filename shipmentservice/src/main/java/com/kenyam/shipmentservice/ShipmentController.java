package com.kenyam.shipmentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Shipment> getOrderAll () {
        return shipmentService.getShipmentAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Shipment getShipmentById (@PathVariable("id") int id) {
        return shipmentService.getShipmentById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{id}")
    public Shipment getShipmentByOrderId (@PathVariable("id") int id) {
        return shipmentService.getShipmentByOrderId(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dispatch/{id}") // Temporarily implemented with GET
    public Shipment dispatchShipmentById (@PathVariable("id") int id) {
        return shipmentService.dispatchShipment(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/complete/{id}") // Temporarily implemented with GET
    public Shipment completeShipmentById (@PathVariable("id") int id) {
        return shipmentService.completeShipment(id);
    }


}
