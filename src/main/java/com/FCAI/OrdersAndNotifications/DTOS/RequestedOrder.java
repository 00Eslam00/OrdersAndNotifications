package com.FCAI.OrdersAndNotifications.DTOS;

import lombok.Data;

import java.util.HashMap;

@Data
public class RequestedOrder {
    private String userName;
    private HashMap<String, Integer> productList;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserName: " + userName + '\n');
        for (var product : productList.entrySet()) {
            builder.append(product.getKey() + " " + product.getValue() + '\n');
        }
        return builder.toString();
    }
}
