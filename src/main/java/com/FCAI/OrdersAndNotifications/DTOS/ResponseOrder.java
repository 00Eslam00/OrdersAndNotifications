package com.FCAI.OrdersAndNotifications.DTOS;

import com.FCAI.OrdersAndNotifications.Models.SimpleOrder;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ResponseOrder {
    private String userName;
    private HashMap<String, Integer> productList;
    private LocalDateTime date;

}
