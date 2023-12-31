package com.FCAI.OrdersAndNotifications.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FCAI.OrdersAndNotifications.DTOS.Response;
import com.FCAI.OrdersAndNotifications.Models.*;
import com.FCAI.OrdersAndNotifications.Repositories.IProductRepo;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductRepo productRepo;

    @GetMapping("/")
    public Response<List<Product>> getAllProduct() {
        return (new Response<List<Product>>(productRepo.getAll()));
    }
}
