package com.kenyam.shipmentservice;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ShipmentRepository {

    private List<Shipment> shipmentList;

    public ShipmentRepository () {
        this.shipmentList = new ArrayList<>();
    }

    public Shipment searchById (int id) throws IllegalArgumentException {
        if (id >= shipmentList.size()) {
            throw new IllegalArgumentException("Invalid id:" + id);
        }
        return this.shipmentList.get(id);
    }

    public Shipment searchByOrderId (int id) throws IllegalArgumentException {
        for (Shipment s : this.shipmentList) {
            if (id == Integer.parseInt(s.getOrderId())) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }

    public List<Shipment> fetchAll () throws NullPointerException {
        if (shipmentList == null) {
            throw new NullPointerException("Shipment list is null");
        }
        return shipmentList;
    }

    public void save (Shipment shipment) throws IllegalArgumentException {
        if (shipment == null) {
            throw new IllegalArgumentException("Received shipment is null");
        }
        shipment.setId(String.valueOf(shipmentList.size()));
        shipmentList.add(shipment);
    }

    public void update (int id, Shipment shipment) throws IllegalArgumentException {
        if (id >= shipmentList.size()) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        shipmentList.set(id, shipment);
    }

}


