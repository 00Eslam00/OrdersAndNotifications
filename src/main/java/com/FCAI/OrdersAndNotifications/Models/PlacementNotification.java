package com.FCAI.OrdersAndNotifications.Models;

public class PlacementNotification extends Notification {
    public PlacementNotification(Order order) {
        super(order);
    }

    public PlacementNotification(Order order, int configuredTimeWithSecs) {
        super(order, configuredTimeWithSecs);
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append("Dear " + this.getOrder().getUserName() + ", your booking of { ");
        for (var or : this.getOrder()) {
            for (var proserial : or.getProductAmount().keySet()) {
                s.append(this.getProRepo().getBySerialNumber(proserial).get().getName() + " ");
            }
        }
        s.append("is confirmed. thanks for using our store :)");
        return s.toString();
    }
}
