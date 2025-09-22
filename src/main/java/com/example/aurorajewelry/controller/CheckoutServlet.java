package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Order;
import com.example.aurorajewelry.repository.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
    private OrderRepository orderRepo;

    @Override
    public void init() {
        orderRepo = new OrderRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy user từ session (nếu có)
        Object userObj = req.getSession().getAttribute("user");
        Integer customerId = null;
        if (userObj != null) {
            // giả sử user có method getId(); sửa theo model user của bạn
            try {
                java.lang.reflect.Method m = userObj.getClass().getMethod("getId");
                Object val = m.invoke(userObj);
                if (val instanceof Integer) customerId = (Integer) val;
                else if (val instanceof Number) customerId = ((Number) val).intValue();
            } catch (Exception ignored) {}
        }

        // Giả sử cart tổng tiền lấy từ session attribute "cartTotal"
        Double total = (Double) req.getSession().getAttribute("cartTotal");
        if (total == null) total = 0.0;

        Order order = new Order();
        order.setCustomerId(customerId); // now OK because customerId is Integer
        order.setOrderDate(new Date());
        order.setTotal(total);

        int orderId = orderRepo.save(order);

        // nếu bạn có OrderDetail lưu chi tiết, lưu vào OrderDetailRepository sau đó

        // dọn giỏ
        req.getSession().removeAttribute("cart");
        req.getSession().removeAttribute("cartTotal");

        resp.sendRedirect(req.getContextPath() + "/order-success?id=" + orderId);
    }
}
