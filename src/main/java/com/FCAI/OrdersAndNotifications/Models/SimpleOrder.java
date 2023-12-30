package com.FCAI.OrdersAndNotifications.Models;

import java.util.HashMap;

import lombok.Data;

@Data
public class SimpleOrder extends Order {

    public SimpleOrder(String userName) {
        this();
        setUserName(userName);
    }
    public SimpleOrder(){
        setProductAmount(new HashMap<>());
    }



    @Override
    public void getDetails() {
        System.out.println("Username : " + getUserName());
        var products = getProductAmount();
        for (var pro : products.entrySet()) {
            System.out.println(pro.getKey() + " " + pro.getValue());
        }
    }

    @Override
    public double calculateTotalPrice() {
        return getProductAmount().values().stream()
                .mapToDouble(price -> price)
                .sum();
    }
}
