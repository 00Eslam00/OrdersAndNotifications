package com.FCAI.OrdersAndNotifications.Controllers;

import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.Factories.OrderFactory;
import com.FCAI.OrdersAndNotifications.Models.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    //username for acc (string)
    @PostMapping("/api/order/")
    List<RequestedOrder> createOrder(@RequestBody List<RequestedOrder> orders) {
        for (var te : orders) {
            System.out.println(te);
        }
        Order order = (new OrderFactory()).makeOrder(orders);
        return orders;
    }

    @GetMapping("/api/order/{username}")
    void getOrders(@PathVariable String userName){
        //List<Order> orders =  (getUserbyUserName(userName)).orders();
    }
}
