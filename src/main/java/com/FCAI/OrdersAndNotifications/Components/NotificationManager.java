package com.FCAI.OrdersAndNotifications.Components;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            placementQueue.add(new Notification(order, 60));
            System.out.println("printing adding");
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
        lock.lock();
        try {
            shipmentQueue.add(new Notification(order));
            startPeriodicCheck();
        } finally {
            lock.unlock();
        }
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

    private void startSceduler() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private void periodicCheck() {
        lock.lock();
        try {
            if (!placementQueue.isEmpty()
                    && placementQueue.get(0).getSentConfiguredTime().isBefore(LocalDateTime.now())) {
                Notification notification = placementQueue.remove(0); // Remove the first element
                // Process the notification
                System.out.println("Processing notification: " + notification);

                if (placementQueue.isEmpty()) {
                    // If the queue becomes empty, shut down the scheduled task
                    scheduler.shutdown();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // Additional methods as needed

    // Cleanup the scheduler on application shutdown
    public void endScheduler() {
        scheduler.shutdown();
    }
}
