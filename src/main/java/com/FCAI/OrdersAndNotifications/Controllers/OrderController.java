package com.FCAI.OrdersAndNotifications.Controllers;

import com.FCAI.OrdersAndNotifications.BusinessLogic.IOrderBl;
import com.FCAI.OrdersAndNotifications.BusinessLogic.OrderBL;
import com.FCAI.OrdersAndNotifications.Components.INotificationManager;
import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.DTOS.Response;
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

    @Autowired
    IOrderBl orderBl;

    @PostMapping("/api/order/")
    Response<Order> createOrder(@RequestBody List<RequestedOrder> orders) {
        Order order = (new OrderFactory()).makeOrder(orders);
        Response<Order> response = new Response<>();
        if (orderBl.valid(order)) {
            orderRepo.add(order);
            notificationManager.addToPlacementQueue(order);
            response.setResponseObj(order);
            return response;
        }
        response.setCode(1);
        response.setMsg("Error!");
        return response;
    }

    @GetMapping("/api/order/{username}")
    List<Order> getUserOrders(@PathVariable String username) {
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
