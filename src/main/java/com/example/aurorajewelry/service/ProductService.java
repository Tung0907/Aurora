package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(int id);
    void add(Product product);
    void update(Product product);
    void delete(int id);
}
