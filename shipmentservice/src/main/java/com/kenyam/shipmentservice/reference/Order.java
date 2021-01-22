package com.kenyam.shipmentservice.reference;

import lombok.Data;

@Data
public class Order {

    private String id;
    private String customer;
    private String item;
    private int qty;
    private String status;

}
