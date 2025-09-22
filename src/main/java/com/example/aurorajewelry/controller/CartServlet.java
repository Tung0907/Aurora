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
    public void init() { productService = new ProductServiceImpl(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);

        if ("clear".equals(action)) {
            cart.clear();
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        req.setAttribute("cart", cart);
        req.setAttribute("total", CartService.getTotal(cart));
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // actions: add, update, remove
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Item> cart = CartService.getCart(session);

        if ("add".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int qty = Integer.parseInt(req.getParameter("quantity"));
            Product p = productService.getById(productId);
            Item it = cart.get(productId);
            if (it == null) cart.put(productId, new Item(p, qty));
            else it.quantity += qty;
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int qty = Integer.parseInt(req.getParameter("quantity"));
            Item it = cart.get(productId);
            if (it != null) it.quantity = qty;
        } else if ("remove".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            cart.remove(productId);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
