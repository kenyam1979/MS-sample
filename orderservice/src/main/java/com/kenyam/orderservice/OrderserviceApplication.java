package com.kenyam.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderserviceApplication {

	@Autowired
	ShipmentQueueListner shipmentQueueListener;

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
