package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Product;
import com.example.aurorajewelry.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {
    private ProductRepository repo;

    @Override
    public void init() {
        repo = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/WEB-INF/views/product-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Product p = repo.findById(id);
                req.setAttribute("product", p);
                req.getRequestDispatcher("/WEB-INF/views/product-form.jsp").forward(req, resp);
                break;
            case "delete":
                repo.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("products");
                break;
            default:
                List<Product> list = repo.findAll();
                req.setAttribute("products", list);
                req.getRequestDispatcher("/WEB-INF/views/product-list.jsp").forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        String image = req.getParameter("image");
        int catId = Integer.parseInt(req.getParameter("categoryId"));

        Product p = new Product(
                id == null || id.isEmpty() ? 0 : Integer.parseInt(id),
                name, desc, price, stock, image, catId
        );

        if (id == null || id.isEmpty()) {
            repo.save(p);
        } else {
            repo.update(p);
        }
        resp.sendRedirect("products");
    }
}
