package com.pluralsight.NorthwindTradersApi.controllers;

import com.pluralsight.NorthwindTradersApi.dao.CategoryDao;
import com.pluralsight.NorthwindTradersApi.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private CategoryDao dao;

    @Autowired
    public CategoriesController(CategoryDao dao) {
        this.dao = dao;
    }

    // respond to http://localhost:8080/categories
    @GetMapping
    public List<Category> getCategories() {
//        List<Category> categories = new ArrayList<>(List.of(
//                new Category(1, "Coffee"),
//                new Category(2, "Tea"),
//                new Category(3, "Cocoa")
//        ));
        List<Category> categories = dao.getCategories();
        return categories;
    }

    // respond to http://localhost:8080/categories/id/{id}
    @GetMapping("/id/{id}")
    public Category getCategory(@PathVariable int id) {
        return dao.getCategory(id);
    }

    // respond to http://localhost:8080/categories/{name}
    @GetMapping("/{name}")
    public Category getCategory(@PathVariable String name) {
        return dao.getCategory(name);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return dao.insert(category);
    }

}