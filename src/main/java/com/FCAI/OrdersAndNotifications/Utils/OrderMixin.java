package com.FCAI.OrdersAndNotifications.Utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public abstract class OrderMixin {
    @JsonIgnore
    @JsonSerialize(using = OrderIDSerializer.class)
    protected Integer orderID;
}