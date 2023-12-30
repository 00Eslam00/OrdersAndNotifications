package com.FCAI.OrdersAndNotifications.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CompoundOrder extends Order{

    private List<Order> orderList = new ArrayList<>();

   /* public CompoundOrder() {
        orderList = new ArrayList<>();
    }*/

    public void add(Order order) {
        orderList.add(order);
    }

    public void remove(Order order) {
        orderList.remove(order);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void getDetails() {
        for(var order:orderList){
            order.getDetails();
        }
    }
}
