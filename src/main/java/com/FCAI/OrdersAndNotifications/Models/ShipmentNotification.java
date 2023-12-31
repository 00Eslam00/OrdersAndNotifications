package com.FCAI.OrdersAndNotifications.Models;

public class ShipmentNotification extends Notification {

    public ShipmentNotification(Order order) {
        super(order);
    }

    public ShipmentNotification(Order order, int configuredTimeWithSecs) {
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
        s.append("is going to be shipped. thanks for using our store :)");
        return s.toString();
    }
}
