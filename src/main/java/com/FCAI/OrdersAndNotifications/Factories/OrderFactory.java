package com.FCAI.OrdersAndNotifications.Factories;

import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.Models.CompoundOrder;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Models.SimpleOrder;

import java.util.List;

public class OrderFactory {
    public Order makeOrder(List<RequestedOrder> order) {
        if (order.size() > 1)
            return new CompoundOrder();
        else
            return new SimpleOrder(order.get(0).getUserName());
    }
}
