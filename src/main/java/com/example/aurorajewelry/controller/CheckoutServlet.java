package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Order;
import com.example.aurorajewelry.model.OrderDetail;
import com.example.aurorajewelry.model.Product;
import com.example.aurorajewelry.repository.OrderDetailRepository;
import com.example.aurorajewelry.repository.OrderRepository;
import com.example.aurorajewelry.service.ProductService;
import com.example.aurorajewelry.service.impl.ProductServiceImpl;
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
    private ProductService productService;

    @Override
    public void init() {
        orderRepo = new OrderRepository();
        orderDetailRepo = new OrderDetailRepository();
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // show checkout page
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);
        req.setAttribute("cart", cart);
        req.setAttribute("total", CartService.getTotal(cart));
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // for simplicity we do not require login; create Order with customerId = null
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);
        double total = CartService.getTotal(cart);

        Order order = new Order();
        order.setCustomerId(null); // guest
        order.setOrderDate(new Date());
        order.setTotal(total);

        int orderId = orderRepo.save(order); // uses updated save to return generated id
        if (orderId <= 0) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không tạo được đơn hàng.");
            return;
        }

        // save order details
        for (Map.Entry<Integer, Item> en : cart.entrySet()) {
            Product p = en.getValue().product;
            int qty = en.getValue().quantity;

            OrderDetail od = new OrderDetail();
            od.setOrderId(orderId);
            od.setProductId(p.getProductId()); // ✅ đúng getter
            od.setQuantity(qty);
            od.setPrice(p.getPrice());

            orderDetailRepo.save(od);
            // optional: giảm số lượng tồn kho nếu muốn
        }

        // clear cart
        cart.clear();

        // redirect to success page or order details
        resp.sendRedirect(req.getContextPath() + "/orders?msg=created");
    }
}
