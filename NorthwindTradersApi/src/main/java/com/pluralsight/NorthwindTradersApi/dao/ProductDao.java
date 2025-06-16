package com.pluralsight.NorthwindTradersApi.dao;

import com.pluralsight.NorthwindTradersApi.models.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAll();
    Product getById(int id);
}
