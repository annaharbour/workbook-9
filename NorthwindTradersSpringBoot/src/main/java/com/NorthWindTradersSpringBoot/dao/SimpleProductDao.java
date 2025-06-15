package com.NorthWindTradersSpringBoot.dao;

import com.NorthWindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDao implements ProductDao {
    private List<Product> products;
    private int nextId = 1;

    public SimpleProductDao() {
        this.products = new ArrayList<>();
        this.products.addAll(List.of(
                new Product(nextId++, "Laptop", new BigDecimal("999.99")),
                new Product(nextId++, "Chair", new BigDecimal("49.99")),
                new Product(nextId++, "Notebook", new BigDecimal("2.99"))
        ));
    }

    @Override
    public void add(Product product) {
        product.setProductId(nextId++);
        products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    public void delete(int productId) {
        products = products.stream()
                .filter(product -> product.getProductId() != productId)
                .toList();
    }

    public void update(int productId, Product updatedProduct) {
        products = products.stream()
                .map(product -> product.getProductId() == productId ? updatedProduct : product)
                .toList();
    }

    public List<Product> search(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .toList();
    }

    public Product search(int id) {
        return products.stream()
                .filter(product -> product.getProductId() == id)
                .findFirst()
                .orElse(null);
    }
}
