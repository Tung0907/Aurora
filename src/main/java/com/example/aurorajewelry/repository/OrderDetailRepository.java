package com.example.aurorajewelry.repository;

import com.example.aurorajewelry.model.OrderDetail;
import java.sql.*;
import java.util.*;

public class OrderDetailRepository {
    private Connection conn;

    public OrderDetailRepository() {
        conn = DbConnection.getConnection();
    }

    public List<OrderDetail> findAll() {
        List<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetail";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("orderId"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public OrderDetail findById(int id) {
        try {
            String sql = "SELECT * FROM OrderDetail WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("orderId"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(OrderDetail od) {
        try {
            String sql = "INSERT INTO OrderDetail(orderId,productId,quantity,price) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(OrderDetail od) {
        try {
            String sql = "UPDATE OrderDetail SET orderId=?, productId=?, quantity=?, price=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getPrice());
            ps.setInt(5, od.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM OrderDetail WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
