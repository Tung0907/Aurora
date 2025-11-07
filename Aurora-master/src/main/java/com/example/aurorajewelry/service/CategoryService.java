package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(int id);
    void add(Category c);
    void update(Category c);
    void delete(int id);
}
