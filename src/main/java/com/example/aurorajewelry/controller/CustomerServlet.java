package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Customer;
import com.example.aurorajewelry.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/admin/customers"})
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new com.example.aurorajewelry.service.impl.CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> list = customerService.getAll();
        req.setAttribute("customers", list);
        req.getRequestDispatcher("/WEB-INF/views/admin/customer-list.jsp").forward(req, resp);
    }
}
