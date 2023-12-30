package com.FCAI.OrdersAndNotifications.Repositories;

import com.FCAI.OrdersAndNotifications.Models.Order;

import java.util.List;

public interface IOrderRepo {
    List<Order> getAllOrders();

    List<Order> getUserOrders(String userName);

    void remove(Order order);

    void add(Order order);

    void display();

}
