package com.FCAI.OrdersAndNotifications.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.FCAI.OrdersAndNotifications.DTOS.RegisterUser;
import com.FCAI.OrdersAndNotifications.DTOS.Response;
import com.FCAI.OrdersAndNotifications.Models.User;
import com.FCAI.OrdersAndNotifications.Repositories.IUserRepo;

@RestController
public class UserController {

    @Autowired
    IUserRepo userRepo;

    @PostMapping("/api/user/")
    Response<User> signup(@RequestBody RegisterUser registeration) {

        Response<User> res = new Response<User>();

        if (!registeration.getPassword().equals(registeration.getConfirmedPassword())) {
            res.setCode(1);
            res.setMsg("sorry password doesn't match confirm password");
            return res;
        }

        if (userRepo.getUserByUserName(registeration.getUserName()).isPresent()) {
            res.setCode(1);
            res.setMsg("sorry, username is used before ");
            return res;
        }

        if (userRepo.getUserByEmail(registeration.getEmail()).isPresent()) {
            res.setCode(1);
            res.setMsg("sorry, this email is used before ");
            return res;
        }

        if (userRepo.getUserByMobile(registeration.getMobileNumber()).isPresent()) {
            res.setCode(1);
            res.setMsg("sorry, mobile number is used before ");
            return res;
        }

        if (registeration.getBalance() < 0) {
            res.setCode(1);
            res.setMsg("enta 3abeet yasta , balance eh elle besaleb");
            return res;
        }
        User newUser = new User();
        newUser.setUserName(registeration.getUserName());
        newUser.setBalance(registeration.getBalance());
        newUser.setEmail(registeration.getEmail());
        newUser.setMobileNumber(registeration.getMobileNumber());

        res.setResponseObj(newUser);
        userRepo.addUser(newUser);
        return res;

    }

    @GetMapping("/api/users/")
    Response<List<User>> getAllUsers() {

        Response<List<User>> res = new Response<List<User>>();
        res.setResponseObj(userRepo.getAllUsers());

        return res;
    }

}
