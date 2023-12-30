package com.FCAI.OrdersAndNotifications.Components;

import java.util.List;

import com.FCAI.OrdersAndNotifications.Models.Notification;
import com.FCAI.OrdersAndNotifications.Models.Order;

public interface INotificationManager {

    public void addToPlacementQueue(Order order);

    public void addToShipmentQueue(Order order);

    public List<Notification> getPlacementQueue();

    public List<Notification> getShipmentQueue();

}
