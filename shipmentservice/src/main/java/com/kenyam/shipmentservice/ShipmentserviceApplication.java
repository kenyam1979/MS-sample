package com.kenyam.shipmentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShipmentserviceApplication {

	@Autowired
	private OrderQueueListener orderQueueListener;

	public static void main(String[] args) {
		SpringApplication.run(ShipmentserviceApplication.class, args);
	}

}
