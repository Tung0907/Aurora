package com.example.aurorajewelry.service.impl;
import com.example.aurorajewelry.model.OrderDetail;
import com.example.aurorajewelry.repository.OrderDetailRepository;
import com.example.aurorajewelry.service.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl() {
        this.orderDetailRepository = new OrderDetailRepository();
    }

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail getById(int id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void update(OrderDetail orderDetail) {
        orderDetailRepository.update(orderDetail);
    }

    @Override
    public void delete(int id) {
        orderDetailRepository.delete(id);
    }
}
