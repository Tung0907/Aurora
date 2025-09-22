package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order getById(int id);
    void save(Order order);
    void update(Order order);
    void delete(int id);
}
