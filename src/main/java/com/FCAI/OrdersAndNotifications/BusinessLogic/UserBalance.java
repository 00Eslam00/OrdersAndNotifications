package com.FCAI.OrdersAndNotifications.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;

import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Repositories.IProductRepo;
import com.FCAI.OrdersAndNotifications.Repositories.IUserRepo;

public class UserBalance implements IUserBalanceBL {

    @Autowired
    IProductRepo proRepo;

    @Autowired
    IUserRepo userRepo;

    @Override
    public void reduceFromUserBalance(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reduceFromUserBalance'");
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

}
