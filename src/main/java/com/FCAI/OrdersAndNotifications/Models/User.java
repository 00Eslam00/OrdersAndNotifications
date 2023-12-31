package com.FCAI.OrdersAndNotifications.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String email;
    private String mobileNumber;
    @JsonIgnore
    private String password;
    private double balance;
}
