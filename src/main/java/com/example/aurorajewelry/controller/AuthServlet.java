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
        String confirmPassword = req.getParameter("confirmPassword");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        // Validation
        if (name == null || name.trim().isEmpty() || name.length() < 2) {
            req.setAttribute("error", "Họ tên phải có ít nhất 2 ký tự");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            req.setAttribute("error", "Email không hợp lệ");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra email đã tồn tại chưa
        Customer existingCustomer = customerRepository.findByEmail(email);
        if (existingCustomer != null) {
            req.setAttribute("error", "Email này đã được sử dụng. Vui lòng chọn email khác.");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        if (password == null || password.length() < 6) {
            req.setAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        // Tạo customer mới với đầy đủ thông tin
        Customer newCustomer = new Customer();
        newCustomer.setName(name.trim());
        newCustomer.setEmail(email.trim().toLowerCase());
        newCustomer.setPassword(password);
        newCustomer.setRole("USER");
        newCustomer.setPhone(phone != null && !phone.trim().isEmpty() ? phone.trim() : null);
        newCustomer.setAddress(address != null && !address.trim().isEmpty() ? address.trim() : null);

        int newId = customerRepository.save(newCustomer);
        boolean success = newId > 0;

        if (success) {
            req.setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại. Email có thể đã tồn tại.");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
        }
    }
}
