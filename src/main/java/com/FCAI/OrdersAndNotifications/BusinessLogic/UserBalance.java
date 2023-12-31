package com.FCAI.OrdersAndNotifications.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Repositories.IProductRepo;
import com.FCAI.OrdersAndNotifications.Repositories.IUserRepo;

@Component
public class UserBalance implements IUserBalanceBL {

    @Autowired
    IProductRepo proRepo;

    @Autowired
    IUserRepo userRepo;

    @Override
    public void reduceFromUserBalance(Order order) {
        for (Order order2 : order) {
            for (var proAmount : order2.getProductAmount().entrySet()) {
                double price = proRepo.getBySerialNumber(proAmount.getKey()).get().getPrice();
                var user = userRepo.getUserByUserName(order2.getUserName()).get();
                user.setBalance(user.getBalance() - (price * (proAmount.getValue())));
            }
        }
    }

    @Override
    public void returnToUserBalance(Order order) {
        for (Order order2 : order) {
            for (var proAmount : order2.getProductAmount().entrySet()) {
                double price = proRepo.getBySerialNumber(proAmount.getKey()).get().getPrice();
                var user = userRepo.getUserByUserName(order2.getUserName()).get();
                user.setBalance(user.getBalance() + (price * (proAmount.getValue())));
            }
        }

    }

    @Override
    public void reduceFromUserFees(Order order) {
        int numOfUers = 0;
        double fees = 20;
        for (Order order2 : order) {
            numOfUers++;
        }

        fees /= numOfUers;

        for (Order order2 : order) {
            var user = userRepo.getUserByUserName(order2.getUserName()).get();
            user.setBalance(user.getBalance() - fees);
        }
    }

}
