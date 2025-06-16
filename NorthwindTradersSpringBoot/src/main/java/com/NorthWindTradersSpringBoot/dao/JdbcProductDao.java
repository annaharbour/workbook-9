package com.NorthWindTradersSpringBoot.dao;

import com.NorthWindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("JdbcProductDao")
public class JdbcProductDao implements ProductDao {

    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO products (ProductName, UnitPrice) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductId, ProductName, UnitPrice FROM products";
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble("UnitPrice")));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void delete(int productId) {
        String sql = "DELETE FROM product WHERE ProductId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int productId, Product updatedProduct) {
        String sql = "UPDATE products SET ProductName = ?, UnitPrice = ? WHERE ProductId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedProduct.getName());
            preparedStatement.setBigDecimal(2, updatedProduct.getPrice());
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   @Override
    public List<Product> search(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductId, ProductName, UnitPrice FROM products WHERE ProductName = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble("UnitPrice")));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
//
//    public Product search(int id) {
//        String sql = "SELECT ProductId, ProductName, UnitPrice FROM product WHERE ProductId = ?";
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                Product product = new Product();
//                product.setProductId(resultSet.getInt("ProductId"));
//                product.setProductName(resultSet.getString("ProductName"));
//                product.setPrice(BigDecimal.valueOf(resultSet.getDouble("UnitPrice")));
//                return product;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}