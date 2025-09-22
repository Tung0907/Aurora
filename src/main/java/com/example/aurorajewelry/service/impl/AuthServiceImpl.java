package com.example.aurorajewelry.service.impl;

import com.example.aurorajewelry.model.Customer;
import com.example.aurorajewelry.repository.CustomerRepository;
import com.example.aurorajewelry.service.AuthService;

import java.util.List;

public class AuthServiceImpl implements AuthService {
    private final CustomerRepository repo;
    public AuthServiceImpl() { this.repo = new CustomerRepository(); }

    @Override
    public Customer login(String email, String password) {
        // if repo has findByEmail use it; else brute-force
        List<Customer> all = repo.findAll();
        for (Customer c : all) {
            if (c.getEmail() != null && c.getEmail().equals(email)
                    && c.getPassword() != null && c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void register(Customer c) {
        repo.save(c);
    }
}
