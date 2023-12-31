package com.FCAI.OrdersAndNotifications.Controllers;

import com.FCAI.OrdersAndNotifications.BusinessLogic.IOrderBl;
import com.FCAI.OrdersAndNotifications.BusinessLogic.IUserBalanceBL;
import com.FCAI.OrdersAndNotifications.Components.INotificationManager;
import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.DTOS.Response;
import com.FCAI.OrdersAndNotifications.Factories.OrderFactory;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Models.SimpleOrder;
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
    @Autowired
    IUserBalanceBL userBalanceBL;

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
    Response<List<Order>> getUserOrders(@PathVariable String username) {
        return (new Response<>(orderRepo.getUserOrders(username)));
    }

    @GetMapping("/api/order/")
    Response<List<Order>> getUserOrders() {
        return (new Response<>(orderRepo.getAllOrders()));
    }

    @DeleteMapping("/api/order/{orderId}")
    Response<Order> cancleOrder(@PathVariable int orderId) {
        Order order = orderRepo.getOrderByID(orderId).get();
        Response<Order> res = new Response<Order>(order);

        int stat = notificationManager.removeFromQueue(orderId);
        if (stat == 0) {
            res.setCode(1);
            res.setMsg("sorry, there is no order with this id or order may be already shipped");
        } else if (stat == 1) {
            res.setMsg("order has been canceld successfully");
            userBalanceBL.returnToUserBalance(order);
            orderBl.returnProductAmount(order);
        } else if (stat == 2) {
            res.setCode(1);
            res.setMsg("sorry, this order is being shipped to its address");
        }
        return res;
    }
}
