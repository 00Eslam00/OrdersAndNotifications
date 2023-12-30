package com.FCAI.OrdersAndNotifications.Factories;

import com.FCAI.OrdersAndNotifications.DTOS.RequestedOrder;
import com.FCAI.OrdersAndNotifications.Models.CompoundOrder;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Models.SimpleOrder;

import java.util.List;

public class OrderFactory {
    public Order makeOrder(List<RequestedOrder> order) {
        Order order1;
        if (order.size() > 1) {
            order1 = new CompoundOrder();
            order1.setUserName(order.get(0).getUserName());
            order1.setProductAmount(order.get(0).getProductList());
            for (int i = 1; i < order.size(); i++) {
                SimpleOrder temp = new SimpleOrder();
                temp.setUserName(order.get(i).getUserName());
                temp.setProductAmount(order.get(i).getProductList());
                order1.add(temp);
            }
        } else {
            order1 = new SimpleOrder(order.get(0).getUserName());
            order1.setProductAmount(order.get(0).getProductList());
        }
        order1.getDetails();
        return order1;
    }
}
