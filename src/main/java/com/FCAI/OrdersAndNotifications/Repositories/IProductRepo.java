package com.FCAI.OrdersAndNotifications.Repositories;

import java.util.List;
import java.util.Optional;

import com.FCAI.OrdersAndNotifications.Models.Product;

public interface IProductRepo {

    List<Product> getAll();

    Optional<Product> getBySerialNumber(String serialNumber);

    List<Product> getAllByCategory(String category);
}
