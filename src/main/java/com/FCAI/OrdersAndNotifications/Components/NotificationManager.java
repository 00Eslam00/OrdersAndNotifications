package com.FCAI.OrdersAndNotifications.Components;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

import com.FCAI.OrdersAndNotifications.Models.Notification;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Models.PlacementNotification;
import com.FCAI.OrdersAndNotifications.Models.ShipmentNotification;

@Component
public class NotificationManager implements INotificationManager {
    private List<Notification> placementQueue = new LinkedList<>();
    private List<Notification> shipmentQueue = new LinkedList<>();

    private ScheduledExecutorService scheduler;
    private final Lock lock = new ReentrantLock();

    @Override
    public void addToPlacementQueue(Order order) {
        lock.lock();
        try {
            placementQueue.add(new PlacementNotification(order, 60));
            startPeriodicCheck();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Notification> getPlacementQueue() {
        return placementQueue;
    }

    @Override
    public List<Notification> getShipmentQueue() {
        return shipmentQueue;
    }

    @Override
    public void addToShipmentQueue(Order order) {
        shipmentQueue.addLast(new ShipmentNotification(order));
    }

    private void startPeriodicCheck() {
        lock.lock();
        try {
            if (placementQueue.size() == 1) {
                // If this is the first element, start the scheduled task
                startSceduler();
                scheduler.scheduleAtFixedRate(this::periodicCheck, 10, 10, TimeUnit.SECONDS);
            }
        } finally {
            lock.unlock();
        }
    }

    private void periodicCheck() {
        lock.lock();
        try {
            if (!placementQueue.isEmpty()
                    && placementQueue.get(0).getSentConfiguredTime().isBefore(LocalDateTime.now())) {
                Notification notification = placementQueue.remove(0); // Remove the
                addToShipmentQueue(notification.getOrder());
                System.out.println(notification);

                if (placementQueue.isEmpty()) {
                    endScheduler();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // Additional methods as needed

    private void startSceduler() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    // Cleanup the scheduler on application shutdown
    private void endScheduler() {
        scheduler.shutdown();
    }

    @Override
    public int removeFromQueue(int orderID) {
        for (int i = 0; i < placementQueue.size(); i++) {
            if (placementQueue.get(i).getOrder().getOrderID() == orderID) {
                placementQueue.remove(i);
                return 1;
            }
        }

        for (int i = 0; i < shipmentQueue.size(); i++) {
            if (shipmentQueue.get(i).getOrder().getOrderID() == orderID) {
                placementQueue.remove(i);
                return 2;
            }
        }
        return 0;
    }
}
