package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer getById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
}
