package com.example.aurorajewelry.repository;

import com.example.aurorajewelry.config.DBConnection;
import com.example.aurorajewelry.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try {
            String sql = "INSERT INTO [Order](customerId, orderDate, total) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if (order.getCustomerId() != null) {
                ps.setInt(1, order.getCustomerId());
            } else {
                ps.setNull(1, Types.INTEGER);
            }
            ps.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
            ps.setDouble(3, order.getTotal());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                order.setId(id); // gán lại id cho đối tượng order
                rs.close();
                ps.close();
                return id;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
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
    public List<Order> findByCustomerId(int customerId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order] WHERE customerId = ? ORDER BY orderDate DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setCustomerId(rs.getInt("customerId"));
                o.setOrderDate(rs.getTimestamp("orderDate"));
                o.setTotal(rs.getDouble("total"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

    // Lấy danh sách đơn hàng kèm tên khách hàng
    public List<Map<String, Object>> findAllWithCustomerName() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String sql = "SELECT o.id, o.customerId, o.orderDate, o.total, c.name as customerName " +
                        "FROM [Order] o " +
                        "LEFT JOIN Customer c ON o.customerId = c.id " +
                        "ORDER BY o.orderDate DESC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", rs.getInt("id"));
                map.put("customerId", rs.getObject("customerId") != null ? rs.getInt("customerId") : null);
                Timestamp ts = rs.getTimestamp("orderDate");
                map.put("orderDate", ts != null ? new Date(ts.getTime()) : null);
                map.put("total", rs.getDouble("total"));
                map.put("customerName", rs.getString("customerName"));
                list.add(map);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
