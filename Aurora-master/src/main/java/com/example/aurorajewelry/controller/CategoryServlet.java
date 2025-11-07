package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Category;
import com.example.aurorajewelry.service.CategoryService;
import com.example.aurorajewelry.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/admin/categories"})
public class CategoryServlet extends HttpServlet {
    private CategoryService service;

    @Override
    public void init() {
        service = new CategoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Category c = service.getById(id);
                req.setAttribute("category", c);
                req.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(req, resp);
                break;
            case "delete":
                service.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
                break;
            default:
                List<Category> list = service.getAll();
                req.setAttribute("categories", list);
                req.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Category c = new Category(id == null || id.isEmpty() ? 0 : Integer.parseInt(id), name);
        if (id == null || id.isEmpty()) service.add(c);
        else service.update(c);

        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }
}
