package com.FCAI.OrdersAndNotifications.Models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import lombok.Data;
import lombok.Setter;

@Data
public class SimpleOrder extends Order {

    public SimpleOrder(String userName) {
        this();
        setUserName(userName);
    }
    public SimpleOrder(){
        setProductAmount(new HashMap<>());
        setDate(LocalDateTime.now());

    }



    @Override
    public void getDetails() {
        System.out.println("Username : " + getUserName());
        var products = getProductAmount();
        for (var pro : products.entrySet()) {
            System.out.println(pro.getKey() + " " + pro.getValue());
        }
    }
}
