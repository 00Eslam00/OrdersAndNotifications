package com.FCAI.OrdersAndNotifications.Components;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.FCAI.OrdersAndNotifications.BusinessLogic.IUserBalanceBL;
import com.FCAI.OrdersAndNotifications.Models.Notification;
import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Models.PlacementNotification;
import com.FCAI.OrdersAndNotifications.Models.ShipmentNotification;
import com.FCAI.OrdersAndNotifications.Repositories.IUserRepo;

@Component
public class NotificationManager implements INotificationManager {
    private List<Notification> placementQueue = new LinkedList<>();
    private List<Notification> shipmentQueue = new LinkedList<>();
    private HashMap<String, Integer> notifiedEmail = new HashMap<>();
    private HashMap<String, Integer> notificationTemp = new HashMap<>();

    @Autowired
    IUserBalanceBL userBalance;

    @Autowired
    IUserRepo userRepo;

    private ScheduledExecutorService scheduler;
    private final Lock lock = new ReentrantLock();

    private void handleAdding(Notification notification) {
        String notificationType = notification.getMyKind();

        String userEmail;

        userEmail = userRepo.getUserByUserName(notification.getOrder().getUserName()).get().getEmail();

        if (notifiedEmail.containsKey(userEmail))
            notifiedEmail.put(userEmail, 1);
        else
            notifiedEmail.put(userEmail, notifiedEmail.get(userEmail) + 1);

        if (notificationTemp.containsKey(notificationType))
            notificationTemp.put(notificationType, 1);
        else
            notificationTemp.put(notificationType, notificationTemp.get(notificationType)
                    + 1);
    }

    @Override
    public void addToPlacementQueue(Order order) {
        lock.lock();
        try {
            Notification n = new PlacementNotification(order, 60);
            placementQueue.add(n);
            handleAdding(n);

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
        userBalance.reduceFromUserFees(order);
        Notification n = new ShipmentNotification(order);
        shipmentQueue.add(n);
        handleAdding(n);
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
                shipmentQueue.remove(i);
                return 2;
            }
        }
        return 0;
    }

    @Override
    public HashMap<String, Integer> getMostNotificationTemplate() {
        return notificationTemp;
    }

    @Override
    public HashMap<String, Integer> getMostNotifiedEmails() {
        return notifiedEmail;
    }
}
