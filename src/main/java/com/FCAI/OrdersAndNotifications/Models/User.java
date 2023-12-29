package com.FCAI.OrdersAndNotifications.Models;

import lombok.Data;

@Data
public class User {
    private int ID;
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private double balance;
}
