package com.pluralsight.NorthwindTradersApi.dao;

import com.pluralsight.NorthwindTradersApi.models.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> getCategories();
    public Category getCategoryById(int id);
}
