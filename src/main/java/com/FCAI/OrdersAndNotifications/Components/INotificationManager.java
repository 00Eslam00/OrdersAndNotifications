package com.FCAI.OrdersAndNotifications.Components;

import java.util.List;

import com.FCAI.OrdersAndNotifications.Models.Notification;
import com.FCAI.OrdersAndNotifications.Models.Order;

public interface INotificationManager {

    void addToPlacementQueue(Order order);

    void addToShipmentQueue(Order order);

    List<Notification> getPlacementQueue();

    List<Notification> getShipmentQueue();

    void removeFromQueue(int orderID);

}
