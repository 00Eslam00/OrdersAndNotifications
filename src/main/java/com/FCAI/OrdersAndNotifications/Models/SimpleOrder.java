package com.FCAI.OrdersAndNotifications.Models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import lombok.Data;
import lombok.Setter;

@Data
public class SimpleOrder extends Order {

    public SimpleOrder(String userName) {
        setDate(LocalDateTime.now());
        setUserName(userName);
        setProductAmount(new HashMap<>());
    }



    @Override
    public void createOrder(String userName) {

    }
}
