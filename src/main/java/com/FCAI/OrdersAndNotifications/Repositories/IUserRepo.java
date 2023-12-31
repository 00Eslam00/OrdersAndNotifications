package com.FCAI.OrdersAndNotifications.Repositories;

import java.util.List;
import java.util.Optional;

import com.FCAI.OrdersAndNotifications.Models.User;

public interface IUserRepo {

    void addUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserByUserName(String userName);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByMobile(String mobile);

}
