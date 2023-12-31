package com.FCAI.OrdersAndNotifications.Repositories;

import com.FCAI.OrdersAndNotifications.Models.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepo {
    List<Order> getAllOrders();

    Optional<Order> getOrderByID(int orderID);
    List<Order> getUserOrders(String userName);

    void remove(Order order);

    void add(Order order);

    void display();

//    void deductBalance(String userName, double amount);

}
