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
            String sql = "SELECT * FROM Orders"; // ✅ đổi tên bảng
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getTimestamp("orderDate"),  // ✅ timestamp
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
            String sql = "SELECT * FROM Orders WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getTimestamp("orderDate"),
                        rs.getDouble("total")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int save(Order o) {
        int generatedId = -1;
        String sql = "INSERT INTO Orders (customerId, orderDate, total) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, o.getCustomerId());
            ps.setTimestamp(2, new java.sql.Timestamp(o.getOrderDate().getTime())); // ✅ lưu cả ngày+giờ
            ps.setDouble(3, o.getTotal());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    o.setId(generatedId);   // ✅ gán lại id vào object
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }


    public void update(Order o) {
        try {
            String sql = "UPDATE Orders SET customerId=?, orderDate=?, total=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            if (o.getCustomerId() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, o.getCustomerId());
            }
            ps.setTimestamp(2, new java.sql.Timestamp(o.getOrderDate().getTime()));
            ps.setDouble(3, o.getTotal());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM Orders WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
