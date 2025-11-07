package com.example.aurorajewelry.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "OrderSuccessServlet", urlPatterns = {"/order-success"})
public class OrderSuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy orderId từ query nếu muốn hiển thị thông tin đơn hàng
        String id = req.getParameter("id");
        req.setAttribute("orderId", id);

        req.getRequestDispatcher("/WEB-INF/views/order-success.jsp").forward(req, resp);
    }
}
