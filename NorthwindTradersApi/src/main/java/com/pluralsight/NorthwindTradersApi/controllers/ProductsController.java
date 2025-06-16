package com.pluralsight.NorthwindTradersApi.controllers;

import com.pluralsight.NorthwindTradersApi.dao.CategoryDao;
import com.pluralsight.NorthwindTradersApi.dao.ProductDao;
import com.pluralsight.NorthwindTradersApi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ProductDao dao;

    @Autowired
    public ProductsController(ProductDao dao) {
        this.dao = dao;
    }


    // respond to http://localhost:8080/categories
    @GetMapping
    public List<Product> getProducts() {
        return dao.getAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return dao.getById(id);
    }

    @GetMapping("category/{id}")
    public List<Product> getProductByCategory(@PathVariable int id) {
        return dao.getByCategoryId(id);
    }


    @GetMapping("/price/{price}")
    public List<Product> getProductById(@PathVariable double price) {
        return dao.getByPrice(price);
    }
}