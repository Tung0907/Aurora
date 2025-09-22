package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.OrderDetail;
import com.example.aurorajewelry.repository.OrderDetailRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/order-details"})
public class OrderDetailListServlet extends HttpServlet {
    private OrderDetailRepository repo;

    @Override
    public void init() {
        repo = new OrderDetailRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderDetail> list = repo.findAll();
        req.setAttribute("details", list);
        req.getRequestDispatcher("/WEB-INF/views/admin/orderdetail-list.jsp").forward(req, resp);
    }
}
