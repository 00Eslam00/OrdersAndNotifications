package com.FCAI.OrdersAndNotifications.Models;

import lombok.Data;

@Data
public class PlacementNotification {
    private Order order;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        return s.toString();
    }

}
