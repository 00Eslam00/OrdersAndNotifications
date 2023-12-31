package com.FCAI.OrdersAndNotifications.DTOS;

import java.util.HashMap;

import lombok.Data;

@Data
public class Statistics {
    private HashMap<String, Integer> notifiedEmail = new HashMap<>();
    private HashMap<String, Integer> notificationTemp = new HashMap<>();
}
