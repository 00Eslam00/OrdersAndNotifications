package com.FCAI.OrdersAndNotifications.Controllers;

import com.FCAI.OrdersAndNotifications.Components.INotificationManager;
import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.Factories.OrderFactory;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Repositories.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    INotificationManager notificationManager;

    @PostMapping("/api/order/")
    List<RequestedOrder> createOrder(@RequestBody List<RequestedOrder> orders) {
        Order order = (new OrderFactory()).makeOrder(orders);
        orderRepo.add(order);
        notificationManager.addToPlacementQueue(order);
        return orders;
    }

    @GetMapping("/api/order/{username}")
    List<Order> getUserOrders(@PathVariable String username) {

        int size = 0;
        for (var order : orderRepo.getUserOrders(username)) {

            for (Order order2 : order) {
                System.out.println("mine " + order2.getUserName() + " " + order2.getOrderID());
            }
        }

        return orderRepo.getUserOrders(username);
    }

    @GetMapping("/api/order/")
    List<Order> getUserOrders() {
        return orderRepo.getAllOrders();
    }

    @DeleteMapping("/api/order/{orderId}")
    void deleteOrder(@PathVariable int orderId) {

    }
}
