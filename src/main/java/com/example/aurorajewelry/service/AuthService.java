package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.Customer;

public interface AuthService {
    Customer login(String email, String password);
    void register(Customer c);
}
