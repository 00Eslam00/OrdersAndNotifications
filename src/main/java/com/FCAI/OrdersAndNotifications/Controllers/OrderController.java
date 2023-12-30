package com.FCAI.OrdersAndNotifications.Controllers;

import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.Factories.OrderFactory;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Repositories.IOrderRepo;
import com.FCAI.OrdersAndNotifications.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    IOrderRepo orderRepo;
    @PostMapping("/api/order/")
    List<RequestedOrder> createOrder(@RequestBody List<RequestedOrder> orders) {
        for (var te : orders) {
            System.out.println(te);
        }
        Order order = (new OrderFactory()).makeOrder(orders);
        orderRepo.add(order);
        return orders;
    }

    @GetMapping("/api/order/{username}")
    List<Order> getUserOrders(@PathVariable String username){
        return orderRepo.getUserOrders(username);
    }

    @GetMapping("/api/order/")
    List<Order> getUserOrders(){
        return orderRepo.getAllOrders();
    }
}
