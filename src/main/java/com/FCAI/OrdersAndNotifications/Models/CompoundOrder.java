package com.FCAI.OrdersAndNotifications.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
// @JsonSerialize(using = ObjectArraySerializer.class)
public class CompoundOrder extends Order {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    private List<Order> orderList = new ArrayList<>();

    @Override
    public void add(Order order) {
        orderList.add(order);
    }

    @Override

    public void remove(Order order) {
        orderList.remove(order);
    }

    @Override

    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void getDetails() {
        System.out.println("Username : " + getUserName());
        var products = getProductAmount();
        for (var pro : products.entrySet()) {
            System.out.println(pro.getKey() + " " + pro.getValue());
        }
        System.out.println("Sub Orders:");
        System.out.println("==========");
        for (var order : orderList) {
            order.getDetails();
        }
    }

    @JsonIgnore
    @Override
    public Iterator<Order> iterator() {
        return new CompoundOrderIterator();
    }

    private class CompoundOrderIterator implements Iterator<Order> {
        private boolean firstIteration = true;
        private Iterator<Order> orderIterator;

        public CompoundOrderIterator() {
            orderIterator = new ArrayList<Order>(orderList).iterator();
        }

        @Override
        public boolean hasNext() {
            if (firstIteration) {
                return true;
            } else {
                return orderIterator.hasNext();
            }
        }

        @Override
        public Order next() {
            if (firstIteration) {
                firstIteration = false;
                return CompoundOrder.this;
            } else {
                return orderIterator.next();
            }
        }
    }

    @JsonIgnore
    @Override
    public double calculateTotalPrice() {
        return getOrderList().stream()
                .mapToDouble(Order::calculateTotalPrice)
                .sum();
    }
}
