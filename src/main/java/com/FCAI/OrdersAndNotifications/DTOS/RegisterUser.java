package com.FCAI.OrdersAndNotifications.DTOS;

import lombok.Data;

@Data
public class RegisterUser {
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private String ConfirmedPassword;
}
