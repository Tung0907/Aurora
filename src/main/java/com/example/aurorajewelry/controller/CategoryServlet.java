package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Category;
import com.example.aurorajewelry.repository.CategoryRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/categories"})
public class CategoryServlet extends HttpServlet {
    private CategoryRepository repo;

    @Override
    public void init() {
        repo = new CategoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/WEB-INF/views/category-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Category c = repo.findById(id);
                req.setAttribute("category", c);
                req.getRequestDispatcher("/WEB-INF/views/category-form.jsp").forward(req, resp);
                break;
            case "delete":
                repo.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("categories");
                break;
            default:
                List<Category> list = repo.findAll();
                req.setAttribute("categories", list);
                req.getRequestDispatcher("/WEB-INF/views/category-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Category c = new Category(id == null || id.isEmpty() ? 0 : Integer.parseInt(id), name);
        if (id == null || id.isEmpty()) {
            repo.save(c);
        } else {
            repo.update(c);
        }
        resp.sendRedirect("categories");
    }
}
