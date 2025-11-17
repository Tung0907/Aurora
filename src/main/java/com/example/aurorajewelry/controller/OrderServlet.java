package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.*;
import com.example.aurorajewelry.repository.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(urlPatterns = {"/admin/orders", "/orders"})
public class OrderServlet extends HttpServlet {
    private OrderRepository orderRepo;
    private OrderDetailRepository detailRepo;
    private ProductRepository productRepo;
    private CustomerRepository customerRepo;

    @Override
    public void init() {
        orderRepo = new OrderRepository();
        detailRepo = new OrderDetailRepository();
        productRepo = new ProductRepository();
        customerRepo = new CustomerRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        String action = req.getParameter("action");

        if (uri.contains("/admin/orders")) {
            // ✅ Chỉ admin mới được xem tất cả đơn
            if (customer == null || !"ADMIN".equals(customer.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                return;
            }

            // Xử lý các action
            if ("create".equals(action)) {
                // Hiển thị form tạo mới
                List<Customer> customers = customerRepo.findAllUsers();
                req.setAttribute("customers", customers);
                req.getRequestDispatcher("/WEB-INF/views/admin/order-form.jsp").forward(req, resp);
                return;
            } else if ("edit".equals(action)) {
                // Hiển thị form sửa
                String idStr = req.getParameter("id");
                if (idStr != null) {
                    try {
                        int id = Integer.parseInt(idStr);
                        Order order = orderRepo.findById(id);
                        if (order != null) {
                            List<Customer> customers = customerRepo.findAllUsers();
                            req.setAttribute("order", order);
                            req.setAttribute("customers", customers);
                            req.getRequestDispatcher("/WEB-INF/views/admin/order-form.jsp").forward(req, resp);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/admin/orders");
                return;
            } else if ("detail".equals(action)) {
                // Hiển thị chi tiết đơn hàng
                String idStr = req.getParameter("id");
                if (idStr != null) {
                    try {
                        int id = Integer.parseInt(idStr);
                        Order order = orderRepo.findById(id);
                        if (order != null) {
                            List<OrderDetail> details = detailRepo.findByOrderId(id);
                            Customer orderCustomer = order.getCustomerId() != null ? 
                                customerRepo.findById(order.getCustomerId()) : null;
                            req.setAttribute("order", order);
                            req.setAttribute("details", details);
                            req.setAttribute("customer", orderCustomer);
                            req.setAttribute("products", productRepo.findAll());
                            req.getRequestDispatcher("/WEB-INF/views/admin/order-detail.jsp").forward(req, resp);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/admin/orders");
                return;
            } else if ("delete".equals(action)) {
                // Xóa đơn hàng
                String idStr = req.getParameter("id");
                if (idStr != null) {
                    try {
                        int id = Integer.parseInt(idStr);
                        // Xóa chi tiết đơn hàng trước
                        List<OrderDetail> details = detailRepo.findByOrderId(id);
                        for (OrderDetail detail : details) {
                            detailRepo.delete(detail.getId());
                        }
                        // Xóa đơn hàng
                        orderRepo.delete(id);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/admin/orders");
                return;
            }

            // Mặc định: hiển thị danh sách
            List<Map<String, Object>> orders = orderRepo.findAllWithCustomerName();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/WEB-INF/views/admin/order-list.jsp").forward(req, resp);

        } else if (uri.contains("/orders")) {
            // ✅ Khách hàng xem đơn riêng của mình
            if (customer == null) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
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
        String uri = req.getRequestURI();
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        String action = req.getParameter("action");

        if (uri.contains("/admin/orders")) {
            // ✅ Chỉ admin mới được quản lý đơn
            if (customer == null || !"ADMIN".equals(customer.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                return;
            }

            if ("save".equals(action)) {
                // Lưu đơn hàng (create hoặc update)
                String idStr = req.getParameter("id");
                String customerIdStr = req.getParameter("customerId");
                String totalStr = req.getParameter("total");
                String orderDateStr = req.getParameter("orderDate");

                try {
                    Integer customerId = customerIdStr != null && !customerIdStr.isEmpty() ? 
                        Integer.parseInt(customerIdStr) : null;
                    double total = Double.parseDouble(totalStr);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    Date orderDate = orderDateStr != null && !orderDateStr.isEmpty() ? 
                        sdf.parse(orderDateStr) : new Date();

                    if (idStr != null && !idStr.isEmpty()) {
                        // Update
                        int id = Integer.parseInt(idStr);
                        Order order = orderRepo.findById(id);
                        if (order != null) {
                            order.setCustomerId(customerId);
                            order.setTotal(total);
                            order.setOrderDate(orderDate);
                            orderRepo.update(order);
                        }
                    } else {
                        // Create
                        Order order = new Order(0, customerId != null ? customerId : 0, orderDate, total);
                        orderRepo.save(order);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resp.sendRedirect(req.getContextPath() + "/admin/orders");
                return;
            }
        } else if (uri.contains("/orders") && action == null) {
            // Tạo đơn hàng từ giỏ hàng (cho khách hàng)
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
}
