package com.FCAI.OrdersAndNotifications.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.FCAI.OrdersAndNotifications.Models.Product;

public class ProductGenerator {

    private static final String[] CATEGORIES = { "Sweets", "Vegetables", "Beverages", "Dairy", "Snacks" };
    private static final String[] VENDORS = { "John", "Kyle", "Alice", "Mia", "David" };

    public static List<Product> generateProducts() {
        List<Product> productList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            product.setSerialNumber(generateSerialNumber());
            product.setName(generateProductName());
            product.setVendor(generateVendor());
            product.setCategory(generateCategory());
            product.setPrice(generateRandomPrice());
            product.setAvailable(random.nextInt(100)); // Random amount, adjust as needed
            productList.add(product);
        }

        return productList;
    }

    private static String generateSerialNumber() {
        // You can implement your own logic for generating serial numbers
        // For simplicity, let's use a simple prefix followed by a random number
        return "SN" + (1000 + new Random().nextInt(9000));
    }

    private static String generateProductName() {
        // You can add more product names as needed
        String[] productNames = { "Cake", "Milk", "Chocolate", "Carrot", "Chips" };
        return productNames[new Random().nextInt(productNames.length)];
    }

    private static String generateVendor() {
        return VENDORS[new Random().nextInt(VENDORS.length)];
    }

    private static String generateCategory() {
        return CATEGORIES[new Random().nextInt(CATEGORIES.length)];
    }

    private static double generateRandomPrice() {
        // Generate a random price with 2 decimal points
        return Math.round((1 + new Random().nextDouble() * 99) * 100.0) / 100.0;
    }
}