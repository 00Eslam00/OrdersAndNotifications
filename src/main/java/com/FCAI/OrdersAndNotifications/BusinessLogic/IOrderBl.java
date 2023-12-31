package com.FCAI.OrdersAndNotifications.BusinessLogic;

import com.FCAI.OrdersAndNotifications.Models.Order;

public interface IOrderBl {
    public boolean valid(Order order);

    public void reduceProductAmount(Order order);
}
