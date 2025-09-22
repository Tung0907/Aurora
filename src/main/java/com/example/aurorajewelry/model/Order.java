package com.example.aurorajewelry.model;

import java.util.Date;

public class Order {
    private int id;
    private Integer customerId; // đổi từ int -> Integer
    private Date orderDate;
    private double total;

    public Order() {}

    public Order(int id, Integer customerId, Date orderDate, double total) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.total = total;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
