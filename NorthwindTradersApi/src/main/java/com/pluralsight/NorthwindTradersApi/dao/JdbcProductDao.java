package com.pluralsight.NorthwindTradersApi.dao;

import com.pluralsight.NorthwindTradersApi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcProductDao implements ProductDao {
    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products;";
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble("UnitPrice")));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        String sql = "SELECT * FROM products WHERE ProductId = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble("UnitPrice")));
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Product> getByPrice(double price) {
        String sql = "SELECT * FROM products WHERE UnitPrice = ?;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble("UnitPrice")));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Product> getByCategoryId(int categoryId) {
        String sql = "SELECT * FROM products WHERE CategoryID = ?;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble("UnitPrice")));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void insert(Product product) {

    }
}
