package com.kenyam.orderservice;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orderList;

    public OrderRepository () {
        this.orderList = new ArrayList<Order>();
    }

    public Order searchById (int id) throws IllegalArgumentException {
        if (id >= orderList.size()) {
            throw new IllegalArgumentException("Invalid id.");
        }
        return this.orderList.get(id);
    }

    public List<Order> fetchAll () throws NullPointerException {
        if (orderList == null) {
            throw new NullPointerException("OrderList is null.");
        }
        return orderList;
    }

    public void save (Order order) throws IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("Order is null.");
        }
        order.setId(String.valueOf(orderList.size()));
        orderList.add(order);
    }

    public void update (int id, Order order) throws IllegalArgumentException {
        if (id >= orderList.size()) {
            throw new IllegalArgumentException("Invalid id.");
        }
        orderList.set(id, order);
    }

}
