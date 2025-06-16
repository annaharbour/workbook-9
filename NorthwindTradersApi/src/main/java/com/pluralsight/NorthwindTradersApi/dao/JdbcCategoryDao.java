package com.pluralsight.NorthwindTradersApi.dao;

import com.pluralsight.NorthwindTradersApi.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
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
}
