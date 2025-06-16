package com.pluralsight.NorthwindTradersApi.dao;

import com.pluralsight.NorthwindTradersApi.models.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategories();
    Category getCategory(int id);
    Category getCategory(String name);
}
