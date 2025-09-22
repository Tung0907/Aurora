package com.example.aurorajewelry.repository;

import com.example.aurorajewelry.model.Order;
import java.sql.*;
import java.util.*;

public class OrderRepository {
    private Connection conn;

    public OrderRepository() {
        conn = DbConnection.getConnection();
    }

    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Order]";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getDate("orderDate"),
                        rs.getDouble("total")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Order findById(int id) {
        try {
            String sql = "SELECT * FROM [Order] WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getDate("orderDate"),
                        rs.getDouble("total")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Order o) {
        try {
            String sql = "INSERT INTO [Order](customerId,orderDate,total) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getCustomerId());
            ps.setDate(2, new java.sql.Date(o.getOrderDate().getTime()));
            ps.setDouble(3, o.getTotal());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Order o) {
        try {
            String sql = "UPDATE [Order] SET customerId=?, orderDate=?, total=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getCustomerId());
            ps.setDate(2, new java.sql.Date(o.getOrderDate().getTime()));
            ps.setDouble(3, o.getTotal());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM [Order] WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
