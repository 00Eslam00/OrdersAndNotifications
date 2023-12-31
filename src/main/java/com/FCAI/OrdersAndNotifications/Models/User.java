package com.FCAI.OrdersAndNotifications.Models;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private double balance;
}
