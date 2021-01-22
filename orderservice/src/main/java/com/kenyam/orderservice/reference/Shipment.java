package com.kenyam.orderservice.reference;

import lombok.Data;

@Data
public class Shipment {

    private String id;
    private String shippingAddress;
    private String shippingDate;
    private String orderId;
    private String status;

}