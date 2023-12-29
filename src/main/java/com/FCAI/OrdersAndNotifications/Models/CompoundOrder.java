package com.FCAI.OrdersAndNotifications.Models;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{

    private List<Order> orderList;

    public CompoundOrder() {
        orderList = new ArrayList<>();
    }

    @Override
    public void add(Order order) {
        orderList.add(order);
    }
    @Override
    public void remove(Order order) {
        orderList.remove(order);
    }
    @Override
    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void createOrder(String userName) {

    }
}
