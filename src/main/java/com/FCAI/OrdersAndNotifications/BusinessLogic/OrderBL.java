package com.FCAI.OrdersAndNotifications.BusinessLogic;

import com.FCAI.OrdersAndNotifications.Models.Order;
import com.FCAI.OrdersAndNotifications.Models.Product;
import com.FCAI.OrdersAndNotifications.Repositories.IProductRepo;
import com.FCAI.OrdersAndNotifications.Repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OrderBL implements IOrderBl {
    @Autowired
    IProductRepo productRepo;
    @Autowired
    IUserRepo userRepo;

    @Override
    public boolean valid(Order order) {
        // user name,balance
        // product name, quantity
        HashMap<String, Integer> totalProductAmount = new HashMap<>();

        for (var or : order) {
            if (!(userRepo.getUserByUserName(or.getUserName()).isPresent()))
                return false;
            Double totalCost = 0.0;
            for (var proAmount : or.getProductAmount().entrySet()) {
                if (!(productRepo.getBySerialNumber(proAmount.getKey()).isPresent()))
                    return false;
                if (totalProductAmount.containsKey(proAmount.getKey()))
                    totalProductAmount.put(proAmount.getKey(),
                            totalProductAmount.get(proAmount.getKey()) + proAmount.getValue());
                else
                    totalProductAmount.put(proAmount.getKey(), proAmount.getValue());
                totalCost += productRepo.getBySerialNumber(proAmount.getKey()).get().getPrice() * proAmount.getValue();
            }
            if (totalCost > userRepo.getUserByUserName(or.getUserName()).get().getBalance())
                return false;
        }
        for (var temp : totalProductAmount.entrySet()) {
            if (productRepo.getBySerialNumber(temp.getKey()).get().getAvailable() < temp.getValue())
                return false;
        }
        reduceProductAmount(order);
        return true;
        // nage7
    }

    @Override
    public void reduceProductAmount(Order order) {
        for (var or : order) {
            for (var proAmount : or.getProductAmount().entrySet()) {
                Product currProduct = productRepo.getBySerialNumber(proAmount.getKey()).get();
                currProduct.setAvailable(currProduct.getAvailable() - proAmount.getValue());
            }
        }
    }
}
