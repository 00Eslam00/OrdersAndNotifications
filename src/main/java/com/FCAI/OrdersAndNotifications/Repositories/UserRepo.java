package com.FCAI.OrdersAndNotifications.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.FCAI.OrdersAndNotifications.Models.User;

@Component
public class UserRepo implements IUserRepo {

    private List<User> users;

    UserRepo() {
        users = new ArrayList<User>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (new ArrayList<User>(users));
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return users.stream().filter(user -> user.getUserName().equals(userName)).findFirst();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public Optional<User> getUserByMobile(String mobile) {
        return users.stream().filter(user -> user.getMobileNumber().equals(mobile)).findFirst();
    }

}
