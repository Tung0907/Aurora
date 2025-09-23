package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.*;
import com.example.aurorajewelry.repository.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin/orders", "/orders"})
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        if (uri.contains("/admin/orders")) {
            // ✅ Chỉ admin mới được xem tất cả đơn
            if (customer == null || !"ADMIN".equals(customer.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            List<Order> orders = orderRepo.findAll();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/WEB-INF/views/admin/order-list.jsp").forward(req, resp);

        } else if (uri.contains("/orders")) {
            // ✅ Khách hàng xem đơn riêng của mình
            if (customer == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            List<Order> orders = orderRepo.findByCustomerId(customer.getId());
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/WEB-INF/views/order-history.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (customer == null || cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        double total = 0;
        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            Product p = productRepo.findById(e.getKey());
            total += p.getPrice() * e.getValue();
        }

        // ✅ Tạo đơn hàng
        Order order = new Order(0, customer.getId(), new Date(), total);
        int orderId = orderRepo.save(order);  // save() nên trả về id đơn

        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            Product p = productRepo.findById(e.getKey());
            OrderDetail d = new OrderDetail(0, orderId, p.getId(), e.getValue(), p.getPrice());
            detailRepo.save(d);
        }

        session.removeAttribute("cart");
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
