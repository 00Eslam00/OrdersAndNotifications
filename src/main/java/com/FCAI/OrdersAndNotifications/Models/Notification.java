package com.FCAI.OrdersAndNotifications.Models;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.FCAI.OrdersAndNotifications.Repositories.IProductRepo;

import lombok.Data;

@Data
public abstract class Notification {
    private Order order;
    private LocalDateTime sentConfiguredTime;

    @Autowired
    private IProductRepo proRepo;

    public Notification(Order order) {
        this.order = order;
        this.sentConfiguredTime = LocalDateTime.now().plusSeconds(120);
    }

    public Notification(Order order, int configuredTimeWithSecs) {
        this.order = order;
        this.sentConfiguredTime = LocalDateTime.now().plusSeconds(configuredTimeWithSecs);

    }

}
