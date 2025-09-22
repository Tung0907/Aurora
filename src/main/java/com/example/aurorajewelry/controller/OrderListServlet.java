package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Order;
import com.example.aurorajewelry.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderListServlet", urlPatterns = {"/orders"})
public class OrderListServlet extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() {
        orderService = new com.example.aurorajewelry.service.impl.OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> list = orderService.getAll();
        req.setAttribute("orders", list);
        req.getRequestDispatcher("/WEB-INF/views/admin/order-list.jsp").forward(req, resp);
    }
}
