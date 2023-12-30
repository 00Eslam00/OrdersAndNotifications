package com.FCAI.OrdersAndNotifications.Models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Notification {
    private Order order;
    private LocalDateTime sentConfiguredTime;

    public Notification(Order order) {
        this.order = order;
        this.sentConfiguredTime = LocalDateTime.now().plusSeconds(120);
    }

    public Notification(Order order, int configuredTimeWithSecs) {
        this.order = order;
        this.sentConfiguredTime = LocalDateTime.now().plusSeconds(configuredTimeWithSecs);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        return s.toString();
    }

}
