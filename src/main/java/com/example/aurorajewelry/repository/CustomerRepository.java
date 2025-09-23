package com.example.aurorajewelry.repository;

import com.example.aurorajewelry.config.DBConnection;
import com.example.aurorajewelry.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private Connection conn;

    public CustomerRepository() {
        conn = DBConnection.getConnection();
    }

    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Customer";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Customer findById(int id) {
        String sql = "SELECT id, name, email, password, role FROM Customer WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setPassword(rs.getString("password"));
                    c.setRole(rs.getString("role"));
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // tiện: tìm theo email (dùng cho login / register check)
    public Customer findByEmail(String email) {
        String sql = "SELECT id, name, email, password, role FROM Customer WHERE email = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setPassword(rs.getString("password"));
                    c.setRole(rs.getString("role"));
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // save (trả về generated id)
    public int save(Customer c) {
        try {
            String sql = "INSERT INTO Customer(name, email, password, role) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPassword());
            ps.setString(4, c.getRole() != null ? c.getRole() : "USER");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                rs.close();
                ps.close();
                return id;
            }
            ps.close();
        } catch (SQLIntegrityConstraintViolationException sicv) {
            System.err.println("Email đã tồn tại: " + c.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void update(Customer c) {
        String sql = "UPDATE Customer SET name=?, email=?, password=?, role=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPassword());
            ps.setString(4, c.getRole() == null ? "USER" : c.getRole());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Customer WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Customer findByEmailAndPassword(String email, String password) {
        try {
            String sql = "SELECT * FROM Customer WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String em = rs.getString("email");
                String pwd = rs.getString("password");
                String role = null;
                try { role = rs.getString("role"); } catch (SQLException e) { /* nếu cột không tồn tại */ }
                if (role == null) role = "USER";
                Customer c = new Customer(id, name, em, pwd, role);
                rs.close();
                ps.close();
                return c;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
