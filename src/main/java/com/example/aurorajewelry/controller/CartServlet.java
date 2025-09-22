package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Product;
import com.example.aurorajewelry.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    private ProductRepository productRepo;

    @Override
    public void init() {
        productRepo = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        if ("add".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            cart.put(id, cart.getOrDefault(id, 0) + 1);
        } else if ("remove".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            cart.remove(id);
        } else if ("clear".equals(action)) {
            cart.clear();
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart");
    }
}
