package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAll();
    OrderDetail getById(int id);
    void save(OrderDetail orderDetail);
    void update(OrderDetail orderDetail);
    void delete(int id);
}
