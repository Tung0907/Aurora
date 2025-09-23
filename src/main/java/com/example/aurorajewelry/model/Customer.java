package com.example.aurorajewelry.model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;   // thêm mới
    private String address; // thêm mới

    public Customer() {
    }

    // Constructor đầy đủ
    public Customer(int id, String name, String email, String password,
                    String role, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.address = address;
    }

    // Constructor đơn giản (khi đăng ký mới chỉ có name, email, password)
    public Customer(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // getter/setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
