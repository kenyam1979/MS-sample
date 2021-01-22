package com.kenyam.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getOrderAll () {
        return orderService.getOrderAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Order getOrderById (@PathVariable("id") int id) {
        return orderService.getOrderById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrder (@Validated @RequestBody Order order) {
        orderService.createOrder(order);
    }
}