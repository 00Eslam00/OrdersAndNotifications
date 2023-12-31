package com.FCAI.OrdersAndNotifications.Models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class SimpleOrder extends Order {

    public SimpleOrder(String userName) {
        this();
        setUserName(userName);
    }

    public SimpleOrder() {
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

    @JsonIgnore
    @Override
    public Iterator<Order> iterator() {
        return Collections.singletonList((Order) this).iterator();
    }

    @Override
    public double calculateTotalPrice() {
        return getProductAmount().values().stream()
                .mapToDouble(price -> price)
                .sum();
    }
}
