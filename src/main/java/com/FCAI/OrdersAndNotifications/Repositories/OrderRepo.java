package com.FCAI.OrdersAndNotifications.Repositories;

import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepo implements IOrderRepo {
    private List<Order> orderList;

    public OrderRepo() {
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
    public List<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public List<Order> getUserOrders(String userName) {
        return orderList.stream().filter(order -> order.getUserName().equals(userName)).toList();
    }

    @Override
    public void display() {
        for(var order:orderList){
            order.getDetails();
        }
    }

//    public void deductBalance(String userName, double amount) {
//        User user = userRepo.getUserByUsername(userName);
//        user.setBalance(user.getBalance() - amount);
//        userRepo.updateUser(user);
//    }
}
