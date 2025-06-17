package com.pluralsight.NorthwindTradersApi.dao;

import com.pluralsight.NorthwindTradersApi.models.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAll();
    Product getById(int id);
    List<Product> getByPrice(double price);
    List<Product> getByCategoryId(int categoryId);
    void insert(Product product);
}
