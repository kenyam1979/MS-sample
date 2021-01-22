package com.kenyam.orderservice;

import lombok.Data;

@Data
public class Order {

    private String id;
    private String customer;
    private String item;
    private int qty;
    private String status;

}
