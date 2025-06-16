package com.pluralsight.NorthwindTradersApi.controllers;

import com.pluralsight.NorthwindTradersApi.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    // respond to http://localhost:8080/categories
    @GetMapping
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>(List.of(
                new Category(1, "Coffee"),
                new Category(2, "Tea"),
                new Category(3, "Cocoa")
        ));
        return categories;
    }

    // respond to http://localhost:8080/categories/{id}
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        return new Category(1, "Coffee");
    }

    // respond to http://localhost:8080/categories/{name}
    @GetMapping("/{name}")
    public Category getCategory(@PathVariable String name) {
        return new Category(1, "Coffee");
    }
}