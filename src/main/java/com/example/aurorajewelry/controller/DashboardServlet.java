package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.service.ProductService;
import com.example.aurorajewelry.service.CategoryService;
import com.example.aurorajewelry.repository.OrderRepository;
import com.example.aurorajewelry.repository.CustomerRepository;
import com.example.aurorajewelry.service.impl.ProductServiceImpl;
import com.example.aurorajewelry.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/admin/dashboard"})
public class DashboardServlet extends HttpServlet {

    private ProductService productService;
    private CategoryService categoryService;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    @Override
    public void init() {
        productService = new ProductServiceImpl();
        categoryService = new CategoryServiceImpl();
        orderRepository = new OrderRepository();
        customerRepository = new CustomerRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy dữ liệu thống kê
        req.setAttribute("products", productService.getAll());
        req.setAttribute("categories", categoryService.getAll());
        req.setAttribute("orders", orderRepository.findAll());
        req.setAttribute("customers", customerRepository.findAll());

        // Forward tới dashboard.jsp
        req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, resp);
    }
}
