package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Product;
import com.example.aurorajewelry.service.ProductService;
import com.example.aurorajewelry.service.impl.ProductServiceImpl;
import com.example.aurorajewelry.service.impl.CartService;
import com.example.aurorajewelry.service.impl.CartService.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        productService = new ProductServiceImpl();
    }

    // GET: hiển thị cart hoặc hỗ trợ link add via GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);

        // Hỗ trợ add bằng GET (link)
        if ("add".equals(action)) {
            String pidStr = req.getParameter("productId");
            if (pidStr == null) pidStr = req.getParameter("id"); // legacy support
            if (pidStr != null) {
                try {
                    int pid = Integer.parseInt(pidStr);
                    int qty = 1;
                    // optional quantity param
                    try { qty = Integer.parseInt(req.getParameter("quantity")); } catch (Exception ignored) {}
                    Product p = productService.getById(pid);
                    if (p != null) {
                        Item it = cart.get(pid);
                        if (it == null) cart.put(pid, new Item(p, qty));
                        else it.quantity += qty;
                    }
                } catch (NumberFormatException ignored) {}
            }
            // cập nhật tổng vào session
            session.setAttribute("cartTotal", CartService.getTotal(cart));
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        // hiển thị giỏ
        req.setAttribute("cart", cart);
        req.setAttribute("total", CartService.getTotal(cart));
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    // POST: update/remove/clear/add (form)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);

        if ("add".equals(action)) {
            String pidStr = req.getParameter("productId");
            if (pidStr == null) pidStr = req.getParameter("id");
            if (pidStr != null) {
                try {
                    int pid = Integer.parseInt(pidStr);
                    int qty = 1;
                    try { qty = Integer.parseInt(req.getParameter("quantity")); } catch (Exception ignored) {}
                    Product p = productService.getById(pid);
                    if (p != null) {
                        Item it = cart.get(pid);
                        if (it == null) cart.put(pid, new Item(p, qty));
                        else it.quantity += qty;
                    }
                } catch (NumberFormatException ignored) {}
            }
        } else if ("update".equals(action)) {
            String pidStr = req.getParameter("productId");
            String qtyStr = req.getParameter("quantity");
            try {
                int pid = Integer.parseInt(pidStr);
                int qty = Integer.parseInt(qtyStr);
                Item it = cart.get(pid);
                if (it != null) it.quantity = qty;
            } catch (Exception ignored) {}
        } else if ("remove".equals(action)) {
            String pidStr = req.getParameter("productId");
            if (pidStr == null) pidStr = req.getParameter("id");
            try {
                int pid = Integer.parseInt(pidStr);
                cart.remove(pid);
            } catch (Exception ignored) {}
        } else if ("clear".equals(action)) {
            cart.clear();
        }

        // cập nhật tổng vào session
        session.setAttribute("cartTotal", CartService.getTotal(cart));
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
