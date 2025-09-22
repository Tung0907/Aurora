package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Product;
import com.example.aurorajewelry.service.ProductService;
import com.example.aurorajewelry.service.impl.ProductServiceImpl;
import com.example.aurorajewelry.service.CategoryService;
import com.example.aurorajewelry.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() {
        productService = new ProductServiceImpl();
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.setAttribute("categories", categoryService.getAll());
                req.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Product p = productService.getById(id);
                req.setAttribute("product", p);
                req.setAttribute("categories", categoryService.getAll());
                req.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(req, resp);
                break;
            case "delete":
                productService.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/products");
                break;
            default:
                List<Product> list = productService.getAll();
                req.setAttribute("products", list);
                req.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        double price = 0; try { price = Double.parseDouble(req.getParameter("price")); } catch (Exception ignored) {}
        int stock = 0; try { stock = Integer.parseInt(req.getParameter("stock")); } catch (Exception ignored) {}
        String image = req.getParameter("image");
        int catId = 0; try { catId = Integer.parseInt(req.getParameter("categoryId")); } catch (Exception ignored) {}

        Product p = new Product(
                (id == null || id.isEmpty()) ? 0 : Integer.parseInt(id),
                name, desc, price, stock, image, catId
        );

        if (id == null || id.isEmpty()) productService.add(p);
        else productService.update(p);

        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
