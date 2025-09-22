package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Customer;
import com.example.aurorajewelry.repository.CustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {
    private CustomerRepository repo;

    @Override
    public void init() {
        repo = new CustomerRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("register".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("register".equals(action)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String pass = req.getParameter("password");
            Customer c = new Customer(0, name, email, pass);
            repo.save(c);
            resp.sendRedirect("auth");
        } else if ("login".equals(action)) {
            String email = req.getParameter("email");
            String pass = req.getParameter("password");

            for (Customer c : repo.findAll()) {
                if (c.getEmail().equals(email) && c.getPassword().equals(pass)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", c);
                    resp.sendRedirect("home");
                    return;
                }
            }
            req.setAttribute("error", "Invalid credentials");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else if ("logout".equals(action)) {
            req.getSession().invalidate();
            resp.sendRedirect("home");
        }
    }
}
