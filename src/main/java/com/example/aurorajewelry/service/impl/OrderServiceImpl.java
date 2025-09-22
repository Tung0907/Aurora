package com.example.aurorajewelry.service.impl;

import com.example.aurorajewelry.model.Order;
import com.example.aurorajewelry.repository.OrderRepository;
import com.example.aurorajewelry.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl() {
        this.orderRepository = new OrderRepository();
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }
}
