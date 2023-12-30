package com.FCAI.OrdersAndNotifications.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FCAI.OrdersAndNotifications.Components.INotificationManager;
import com.FCAI.OrdersAndNotifications.Models.Notification;

@RestController
public class NotificationsController {

    @Autowired
    INotificationManager notificationManager;

    @GetMapping("/api/notification/")
    public List<Notification> getAllNoty() {
        return notificationManager.getPlacementQueue();
    }
}
