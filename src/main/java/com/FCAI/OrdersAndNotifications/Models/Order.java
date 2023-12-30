package com.FCAI.OrdersAndNotifications.Models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleOrder.class, name = "simple"),
        @JsonSubTypes.Type(value = CompoundOrder.class, name = "compound")
})
public abstract class Order {
    protected String userName;
    protected LocalDateTime date;
    protected HashMap<String, Integer> productAmount;

    public void add(Order order) {
        throw new UnsupportedOperationException();
    }

    public void remove(Order order) {
        throw new UnsupportedOperationException();
    }

    public List<Order> getOrderList() {
        throw new UnsupportedOperationException();
    }

    abstract public void getDetails();
}
