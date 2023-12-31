package com.FCAI.OrdersAndNotifications.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleOrder.class, name = "simple"),
        @JsonSubTypes.Type(value = CompoundOrder.class, name = "compound")
})
public abstract class Order implements Iterable<Order> {
    protected String userName;
    protected LocalDateTime date = LocalDateTime.now();
    protected HashMap<String, Integer> productAmount;
    protected Integer orderID;

    @JsonIgnore
    public void add(Order order) {
        throw new UnsupportedOperationException();
    }

    @JsonIgnore
    public void remove(Order order) {
        throw new UnsupportedOperationException();
    }

    @JsonIgnore
    public List<Order> getOrderList() {
        throw new UnsupportedOperationException();
    }

    @JsonIgnore
    @Override
    public Iterator<Order> iterator() {
        return Collections.singletonList(this).iterator();
    }

    abstract public void getDetails();

    public abstract double calculateTotalPrice();
}
