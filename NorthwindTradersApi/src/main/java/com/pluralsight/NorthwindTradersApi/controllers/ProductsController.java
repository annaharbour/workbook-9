package com.pluralsight.NorthwindTradersApi.controllers;

import com.pluralsight.NorthwindTradersApi.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {
    // respond to http://localhost:8080/categories
    @GetMapping
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>(List.of(
                new Product(1, "Starbucks Breakfast Blend", new BigDecimal("7.22"), 1),
                new Product(2, "Matcha", new BigDecimal("7.22"), 2),
                new Product(3, "Ghirardelli Cocoa Powder", new BigDecimal("6"), 3)
        ));
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return new Product(1, "Starbucks Breakfast Blend", new BigDecimal("7.22"), 1);
    }

    @GetMapping("category/{id}")
    public List<Product> getProductByCategory(@PathVariable int id) {
        List<Product> products = new ArrayList<>(List.of(
                new Product(1, "Starbucks Breakfast Blend", new BigDecimal("7.22"), 1),
                new Product(2, "Dilworth Coffee", new BigDecimal("7.22"), 2),
                new Product(3, "", new BigDecimal("7.22"), 3)
        ));
        return products.stream()
                .filter(p -> id == p.getCategoryId())
                .collect(Collectors.toList());
    }


    @GetMapping("/price/{price}")
    public List<Product> getProductById(@PathVariable double price) {
        List<Product> products = new ArrayList<>(List.of(
                new Product(1, "Starbucks Breakfast Blend", new BigDecimal("7.22"), 1),
                new Product(2, "Matcha", new BigDecimal("7.22"), 2),
                new Product(3, "Ghirardelli Cocoa Powder", new BigDecimal("7.22"), 3)
        ));
        return products.stream()
                .filter(p -> p.getPrice().compareTo(new BigDecimal(price)) < 0)
                .collect(Collectors.toList());
    }
}