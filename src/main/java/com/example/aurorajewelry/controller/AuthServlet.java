package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Customer;
import com.example.aurorajewelry.repository.CustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private CustomerRepository customerRepository;

    @Override
    public void init() {
        customerRepository = new CustomerRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("login")) {
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
        } else if (action.equals("register")) {
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
        } else if (action.equals("logout")) {
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("login".equals(action)) {
            handleLogin(req, resp);
        } else if ("register".equals(action)) {
            handleRegister(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Customer customer = customerRepository.findByEmailAndPassword(email, password);

        if (customer != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", customer);
            session.setAttribute("role", customer.getRole()); // <-- quan trọng

            if ("ADMIN".equalsIgnoreCase(customer.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            req.setAttribute("error", "Email hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // tạo user mặc định role = USER
        Customer newCustomer = new Customer(name, email, password, "USER");

        int newId = customerRepository.save(newCustomer);
        boolean success = newId > 0;

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
        } else {
            req.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại (email có thể bị trùng)");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
        }
    }
}
