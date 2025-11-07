package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.OrderDetail;
import com.example.aurorajewelry.service.OrderDetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetailListServlet", urlPatterns = {"/order-details"})
public class OrderDetailListServlet extends HttpServlet {
    private OrderDetailService detailService;

    @Override
    public void init() {
        detailService = new com.example.aurorajewelry.service.impl.OrderDetailServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderDetail> list = detailService.getAll();
        req.setAttribute("details", list);
        req.getRequestDispatcher("/WEB-INF/views/admin/orderdetail-list.jsp").forward(req, resp);
    }
}
