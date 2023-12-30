package com.FCAI.OrdersAndNotifications.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.FCAI.OrdersAndNotifications.Models.*;
import com.FCAI.OrdersAndNotifications.Repositories.IProductRepo;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductRepo productRepo;

    @GetMapping("/")
    public List<Product> getAllProduct() {
        return productRepo.getAll();
    }
}
