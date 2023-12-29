package com.FCAI.OrdersAndNotifications.Models;

import lombok.Data;

@Data
public class Product {
    private String serialNumber;
    private String name;
    private String vendor;
    private String Category;
    private double price;
    private int available;
}
