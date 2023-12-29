package com.FCAI.OrdersAndNotifications.Components;

import java.util.List;

import com.FCAI.OrdersAndNotifications.Models.PlacementNotification;

import lombok.Data;

@Data
public class PlacementOrderNotificationQeue {

    private List<PlacementNotification> notifications;
}
