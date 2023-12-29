package com.FCAI.OrdersAndNotifications.Models;

import java.time.LocalDateTime;
import java.util.HashMap;
import lombok.Data;

@Data
public class SimpleOrder {
    private int userId;
    private LocalDateTime date;
    private HashMap<String, Integer> productAmount;
}
