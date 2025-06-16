package com.pluralsight.NorthwindTradersApi.models;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String name;
    private BigDecimal price;
    private int categoryId;

    public Product() {

    }

    public Product(int productId, String name, BigDecimal price, int categoryId) {
        this.price = price;
        this.name = name;
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProductId(int i) {
        productId = i;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return productId + ". " + name + "\t$" + String.format(String.valueOf(price), .2f);
    }

    public int getCategoryId() {
        return  categoryId;
    }
}
