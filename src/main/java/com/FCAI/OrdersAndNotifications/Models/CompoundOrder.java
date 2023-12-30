package com.FCAI.OrdersAndNotifications.Models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompoundOrder extends Order {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    private List<Order> orderList = new ArrayList<>();

    @Override
    public void add(Order order) {
        orderList.add(order);
    }

    @Override

    public void remove(Order order) {
        orderList.remove(order);
    }

    @Override

    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void getDetails() {
        System.out.println("Username : " + getUserName());
        var products = getProductAmount();
        for (var pro : products.entrySet()) {
            System.out.println(pro.getKey() + " " + pro.getValue());
        }
        System.out.println("Sub Orders:");
        System.out.println("==========");
        for (var order : orderList) {
            order.getDetails();
        }
    }
}
