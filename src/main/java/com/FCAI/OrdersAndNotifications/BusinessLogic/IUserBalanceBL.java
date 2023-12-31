package com.FCAI.OrdersAndNotifications.BusinessLogic;

import com.FCAI.OrdersAndNotifications.Models.Order;

public interface IUserBalanceBL {

    void reduceFromUserBalance(Order order);

    void reduceFromUserFees(Order order);

    void returnToUserBalance(Order order);
}
