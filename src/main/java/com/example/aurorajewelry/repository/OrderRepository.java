package com.example.aurorajewelry.repository;

import com.example.aurorajewelry.config.DBConnection;
import com.example.aurorajewelry.model.Order;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepository {
    private Connection conn;

    public OrderRepository() {
        conn = DBConnection.getConnection();
    }

    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Order]";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Integer custId = rs.getObject("customerId") != null ? rs.getInt("customerId") : null;
                Timestamp ts = rs.getTimestamp("orderDate");
                Date od = ts != null ? new Date(ts.getTime()) : null;
                list.add(new Order(rs.getInt("id"), custId, od, rs.getDouble("total")));
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
                Integer custId = rs.getObject("customerId") != null ? rs.getInt("customerId") : null;
                Timestamp ts = rs.getTimestamp("orderDate");
                Date od = ts != null ? new Date(ts.getTime()) : null;
                return new Order(rs.getInt("id"), custId, od, rs.getDouble("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // trả về generated id (SCOPE_IDENTITY)
    public int save(Order order) {
        String sql = "INSERT INTO [Order] (customerId, orderDate, total) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getCustomerId());
            ps.setTimestamp(2, new java.sql.Timestamp(order.getOrderDate().getTime()));
            ps.setBigDecimal(3, BigDecimal.valueOf(order.getTotal()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    order.setId(id);        // gán lại vào model
                    return id;              // ✅ trả về id
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public void update(Order o) {
        try {
            String sql = "UPDATE [Order] SET customerId=?, orderDate=?, total=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            if (o.getCustomerId() != null) ps.setInt(1, o.getCustomerId());
            else ps.setNull(1, Types.INTEGER);
            ps.setTimestamp(2, new Timestamp(o.getOrderDate().getTime()));
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
