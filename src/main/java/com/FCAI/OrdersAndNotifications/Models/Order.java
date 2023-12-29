package com.FCAI.OrdersAndNotifications.Models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
@Data
public abstract class Order {
    protected String userName;
    protected LocalDateTime date;
    protected HashMap<String, Integer> productAmount;
    abstract public void createOrder(String userName);
    public void add(Order order) {
        throw new UnsupportedOperationException();
    }

    public void remove(Order order) {
        throw new UnsupportedOperationException();
    }

    public List<Order> getOrderList() {
        throw new UnsupportedOperationException();
    }
}
