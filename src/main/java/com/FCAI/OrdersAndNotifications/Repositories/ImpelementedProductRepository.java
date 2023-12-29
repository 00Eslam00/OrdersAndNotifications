package com.FCAI.OrdersAndNotifications.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.FCAI.OrdersAndNotifications.Models.Product;
import com.FCAI.OrdersAndNotifications.Utils.ProductGenerator;

@Service
public class ImpelementedProductRepository implements ProductRepository {

    private List<Product> products;

    public ImpelementedProductRepository() {
        products = ProductGenerator.generateProducts();
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Optional<Product> getBySerialNumber(String serialNumber) {
        return products.stream().filter(pro -> pro.getSerialNumber().equals(serialNumber)).findFirst();
    }

    @Override
    public List<Product> getAllByCategory(String category) {
        return products.stream().filter(pro -> pro.getCategory().equals(category)).collect(Collectors.toList());
    }

}
