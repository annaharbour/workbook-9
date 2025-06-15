package com.NorthWindTradersSpringBoot.dao;

import com.NorthWindTradersSpringBoot.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductDao {
    public void add(Product product);

    public List<Product> getAll();

    public void delete(int productId);

    public void update(int productId, Product updatedProduct);

    public List<Product> search(String name);
}
