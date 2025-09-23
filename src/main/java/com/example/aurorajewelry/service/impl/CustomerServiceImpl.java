package com.example.aurorajewelry.service.impl;

import com.example.aurorajewelry.model.Customer;
import com.example.aurorajewelry.repository.CustomerRepository;
import com.example.aurorajewelry.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repo;
    public CustomerServiceImpl() { this.repo = new CustomerRepository(); }
    @Override public List<Customer> getAll() { return repo.findAllUsers(); }
    @Override public Customer getById(int id) { return repo.findById(id); }
    @Override public Customer findByEmail(String email) {
        // your repo doesn't have findByEmail — if it doesn't, add method; else call it
        // Fallback: load all and find
        List<Customer> list = repo.findAllUsers();
        for (Customer c : list) if (email != null && email.equals(c.getEmail())) return c;
        return null;
    }
    @Override public void add(Customer c) { repo.save(c); }
    @Override public void update(Customer c) { repo.update(c); }
    @Override public void delete(int id) { repo.delete(id); }
}
