package com.FCAI.OrdersAndNotifications.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FCAI.OrdersAndNotifications.Components.INotificationManager;
import com.FCAI.OrdersAndNotifications.DTOS.Response;
import com.FCAI.OrdersAndNotifications.DTOS.Statistics;

@RestController
public class StatisticsController {

    @Autowired
    INotificationManager notificationManager;

    @GetMapping("/api/notification/")
    public Response<Statistics> getAllNoty() {
        Statistics s = new Statistics();
        s.setNotificationTemp(notificationManager.getMostNotificationTemplate());
        s.setNotifiedEmail(notificationManager.getMostNotifiedEmails());
        return (new Response<>(s));
    }
}
