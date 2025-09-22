package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer getById(int id);
    Customer findByEmail(String email);
    void add(Customer c);
    void update(Customer c);
    void delete(int id);
}
