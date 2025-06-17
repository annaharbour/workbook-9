package com.pluralsight.NorthwindTradersApi.dao;

import com.pluralsight.NorthwindTradersApi.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCategoryDao implements CategoryDao {
    private DataSource dataSource;

    @Autowired
    public JdbcCategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories;";
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("CategoryID"));
                category.setCategoryName(resultSet.getString("CategoryName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return categories;
    }

    @Override
    public Category getCategory(int id) {
        String sql = "SELECT * FROM categories WHERE CategoryID = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("CategoryID"));
                category.setCategoryName(resultSet.getString("CategoryName"));
                return category;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Category getCategory(String name) {
        String sql = "SELECT * FROM categories WHERE CategoryName = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("CategoryID"));
                category.setCategoryName(resultSet.getString("CategoryName"));
                return category;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Category insert(Category category) {
        String sql = "INSERT INTO categories (CategoryName) VALUES(?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();
            System.out.println("Successfully inserted category " + category.getCategoryName());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return category;
    }

    @Override
    public void update(int id, Category category) {
        String sql = "UPDATE categories SET CategoryName = ? WHERE CategoryID = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated category: " + category.getCategoryName());
            } else {
                System.out.println("No category found with ID " + category.getCategoryId());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
