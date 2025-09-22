package com.example.aurorajewelry.repository;

import com.example.aurorajewelry.config.DBConnection;
import com.example.aurorajewelry.model.Product;
import java.sql.*;
import java.util.*;

public class ProductRepository {
    private Connection conn;

    public ProductRepository() {
        conn = DBConnection.getConnection();
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT ProductId, ProductCode, Name, Material, Size, Price, Stock, IsActive, Image, CategoryId FROM Product WHERE IsActive = 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setProductCode(rs.getString("ProductCode"));
                p.setName(rs.getString("Name"));
                p.setMaterial(rs.getString("Material"));
                p.setSize(rs.getString("Size"));
                p.setPrice(rs.getDouble("Price"));
                p.setStock(rs.getInt("Stock"));
                p.setActive(rs.getBoolean("IsActive"));
                p.setImage(rs.getString("Image"));
                p.setCategoryId(rs.getInt("CategoryId"));

                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(int id) {
        try {
            String sql = "SELECT ProductId, ProductCode, Name, Material, Size, Price, Stock, IsActive, Image, CategoryId FROM Product WHERE ProductId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setProductCode(rs.getString("ProductCode"));
                p.setName(rs.getString("Name"));
                p.setMaterial(rs.getString("Material"));
                p.setSize(rs.getString("Size"));
                p.setPrice(rs.getDouble("Price"));
                p.setStock(rs.getInt("Stock"));
                p.setActive(rs.getBoolean("IsActive"));
                p.setImage(rs.getString("Image"));
                p.setCategoryId(rs.getInt("CategoryId"));

                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Product p) {
        try {
            String sql = "INSERT INTO Product(ProductCode, Name, Material, Size, Price, Stock, IsActive, Image, CategoryId) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getName());
            ps.setString(3, p.getMaterial());
            ps.setString(4, p.getSize());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getStock());
            ps.setBoolean(7, p.isActive());
            ps.setString(8, p.getImage());
            ps.setInt(9, p.getCategoryId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product p) {
        try {
            String sql = "UPDATE Product SET ProductCode=?, Name=?, Material=?, Size=?, Price=?, Stock=?, IsActive=?, Image=?, CategoryId=? WHERE ProductId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getName());
            ps.setString(3, p.getMaterial());
            ps.setString(4, p.getSize());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getStock());
            ps.setBoolean(7, p.isActive());
            ps.setString(8, p.getImage());
            ps.setInt(9, p.getCategoryId());
            ps.setInt(10, p.getProductId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM Product WHERE ProductId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
