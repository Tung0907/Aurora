package com.example.aurorajewelry.service;

import com.example.aurorajewelry.model.Category;
import com.example.aurorajewelry.repository.CategoryRepository;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository repo;

    public CategoryServiceImpl() {
        repo = new CategoryRepository();
    }

    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public Category getById(int id) {
        return repo.findById(id);
    }

    @Override
    public void add(Category c) {
        repo.save(c);
    }

    @Override
    public void update(Category c) {
        repo.update(c);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
