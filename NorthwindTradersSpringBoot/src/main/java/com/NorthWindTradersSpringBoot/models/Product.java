package com.NorthWindTradersSpringBoot.models;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String name;
    private BigDecimal price;

    public Product() {

    }

    public Product(int productId, String name, BigDecimal price) {
        this.price = price;
        this.name = name;
        this.productId = productId;
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
}
