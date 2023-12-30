package com.FCAI.OrdersAndNotifications.Controllers;

import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.Factories.OrderFactory;
import com.FCAI.OrdersAndNotifications.Models.CompoundOrder;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Repositories.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    IOrderRepo orderRepo;
    @PostMapping("/api/order/")
    List<RequestedOrder> createOrder(@RequestBody List<RequestedOrder> orders) {
        Order order = (new OrderFactory()).makeOrder(orders);
        orderRepo.add(order);
//        if (order instanceof CompoundOrder) {
//            order.getOrderList().forEach(subOrder -> {
//                orderRepo.deductBalance(subOrder.getUserName(), subOrder.calculateTotalPrice());
//            });
//        } else {
//            orderRepo.deductBalance(order.getUserName(), order.calculateTotalPrice());
//        }
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
