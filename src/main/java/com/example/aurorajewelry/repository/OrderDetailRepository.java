package com.example.aurorajewelry.repository;

import com.example.aurorajewelry.config.DBConnection;
import com.example.aurorajewelry.model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepository {
    private Connection conn;

    public OrderDetailRepository() {
        conn = DBConnection.getConnection();
    }
    // Thêm vào OrderDetailRepository
    public void save(OrderDetail orderDetail) {
        save(orderDetail.getOrderId(),
                orderDetail.getProductId(),
                orderDetail.getQuantity(),
                orderDetail.getPrice());
    }

    // CREATE
    public void save(int orderId, int productId, int quantity, double price) {
        try {
            String sql = "INSERT INTO OrderDetail(orderId, productId, quantity, price) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<OrderDetail> findAll() {
        List<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetail";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setId(rs.getInt("id"));
                od.setOrderId(rs.getInt("orderId"));
                od.setProductId(rs.getInt("productId"));
                od.setQuantity(rs.getInt("quantity"));
                od.setPrice(rs.getDouble("price"));
                list.add(od);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ by ID
    public OrderDetail findById(int id) {
        try {
            String sql = "SELECT * FROM OrderDetail WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setId(rs.getInt("id"));
                od.setOrderId(rs.getInt("orderId"));
                od.setProductId(rs.getInt("productId"));
                od.setQuantity(rs.getInt("quantity"));
                od.setPrice(rs.getDouble("price"));
                return od;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void update(OrderDetail orderDetail) {
        try {
            String sql = "UPDATE OrderDetail SET orderId=?, productId=?, quantity=?, price=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderDetail.getOrderId());
            ps.setInt(2, orderDetail.getProductId());
            ps.setInt(3, orderDetail.getQuantity());
            ps.setDouble(4, orderDetail.getPrice());
            ps.setInt(5, orderDetail.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        try {
            String sql = "DELETE FROM OrderDetail WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm tất cả chi tiết theo orderId
    public List<OrderDetail> findByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetail WHERE orderId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setId(rs.getInt("id"));
                od.setOrderId(rs.getInt("orderId"));
                od.setProductId(rs.getInt("productId"));
                od.setQuantity(rs.getInt("quantity"));
                od.setPrice(rs.getDouble("price"));
                list.add(od);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
