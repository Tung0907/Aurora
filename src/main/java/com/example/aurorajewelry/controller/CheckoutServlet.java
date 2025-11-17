package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Customer;
import com.example.aurorajewelry.model.Order;
import com.example.aurorajewelry.model.Product;
import com.example.aurorajewelry.repository.CustomerRepository;
import com.example.aurorajewelry.repository.OrderRepository;
import com.example.aurorajewelry.repository.OrderDetailRepository;
import com.example.aurorajewelry.repository.ProductRepository;
import com.example.aurorajewelry.service.impl.CartService;
import com.example.aurorajewelry.service.impl.CartService.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
    private OrderRepository orderRepo;
    private OrderDetailRepository orderDetailRepo;
    private ProductRepository productRepo;
    private CustomerRepository customerRepo;

    @Override
    public void init() {
        orderRepo = new OrderRepository();
        orderDetailRepo = new OrderDetailRepository();
        productRepo = new ProductRepository();
        customerRepo = new CustomerRepository();
    }

    // show form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // lấy user id nếu login (tùy model user)
        Integer customerId = null;
        Object userObj = session.getAttribute("user");
        if (userObj != null) {
            try {
                java.lang.reflect.Method m = userObj.getClass().getMethod("getId");
                Object val = m.invoke(userObj);
                if (val instanceof Number) customerId = ((Number) val).intValue();
            } catch (Exception ignored) {}
        }
        req.setAttribute("customer", customerRepo.findById(customerId));
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }

    // submit order
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // lấy user id nếu login (tùy model user)
        Integer customerId = null;
        Object userObj = session.getAttribute("user");
        if (userObj != null) {
            try {
                java.lang.reflect.Method m = userObj.getClass().getMethod("getId");
                Object val = m.invoke(userObj);
                if (val instanceof Number) customerId = ((Number) val).intValue();
            } catch (Exception ignored) {}
        }

        // tổng tiền tính từ cart
        Map<Integer, Item> cart = CartService.getCart(session);
        double total = CartService.getTotal(cart);

        // thông tin khách hàng (nếu form thu)
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        Customer customer = customerRepo.findById(customerId);
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAddress(address);
        customerRepo.update(customer);

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setOrderDate(new Date());
        order.setTotal(total);
        // nếu bạn có fields khác (name/phone/address) thêm setter tương ứng

        int orderId = orderRepo.save(order); // save trả về id (sửa repository để trả id)

        // lưu chi tiết
        for (Item it : cart.values()) {
            orderDetailRepo.save(
                    orderId,
                    it.getProduct().getId(),
                    it.getQuantity(),
                    it.getProduct().getPrice()
            );


            // cập nhật số lượng
            Product oldprod = productRepo.findById(it.getProduct().getId());
            oldprod.setStock(oldprod.getStock() - it.getQuantity());
            productRepo.update(oldprod);
        }


        // clear cart
        cart.clear();
        session.removeAttribute("cart");
        session.removeAttribute("cartTotal");

        resp.sendRedirect(req.getContextPath() + "/order-success?id=" + orderId);
    }
}
