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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);
        req.setAttribute("cart", cart);
        req.setAttribute("total", CartService.getTotal(cart));
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);

        if ("add".equals(action)) {
            int pid = Integer.parseInt(req.getParameter("id"));
            int qty = 1;
            try { qty = Integer.parseInt(req.getParameter("quantity")); } catch (Exception ignored) {}
            Product p = productService.getById(pid);
            Item it = cart.get(pid);
            if (it == null) cart.put(pid, new Item(p, qty));
            else it.quantity += qty;
        } else if ("update".equals(action)) {
            int pid = Integer.parseInt(req.getParameter("id"));
            int qty = Integer.parseInt(req.getParameter("quantity"));
            Item it = cart.get(pid);
            if (it != null) it.quantity = qty;
        } else if ("remove".equals(action)) {
            int pid = Integer.parseInt(req.getParameter("id"));
            cart.remove(pid);
        } else if ("clear".equals(action)) {
            cart.clear();
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
