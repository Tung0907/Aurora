package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.*;
import com.example.aurorajewelry.repository.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "OrderServlet", urlPatterns = {"/admin/orders"})
public class OrderServlet extends HttpServlet {
    private OrderRepository orderRepo;
    private OrderDetailRepository detailRepo;
    private ProductRepository productRepo;

    @Override
    public void init() {
        orderRepo = new OrderRepository();
        detailRepo = new OrderDetailRepository();
        productRepo = new ProductRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (customer == null || cart == null || cart.isEmpty()) {
            resp.sendRedirect("cart");
            return;
        }

        double total = 0;
        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            Product p = productRepo.findById(e.getKey());
            total += p.getPrice() * e.getValue();
        }

        // Tạo đơn hàng
        Order order = new Order(0, customer.getId(), new Date(), total);
        orderRepo.save(order);
        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            Product p = productRepo.findById(e.getKey());
            OrderDetail d = new OrderDetail(0, order.getId(), p.getCategoryId(), e.getValue(), p.getPrice());
            detailRepo.save(d);
        }
        session.removeAttribute("cart");
        resp.sendRedirect("home");
    }
}
