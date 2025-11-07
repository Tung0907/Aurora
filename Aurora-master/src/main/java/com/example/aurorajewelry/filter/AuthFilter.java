package com.example.aurorajewelry.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nếu không cần init gì đặc biệt thì để trống
    }

    @Override
    public void destroy() {
        // Nếu không cần cleanup thì để trống
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

        // chặn admin page nếu chưa login hoặc không phải admin
        if (uri.startsWith(req.getContextPath() + "/admin")) {
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect(req.getContextPath() + "/auth");
                return;
            }
            String role = (String) session.getAttribute("role");
            if (role == null || !role.equals("ADMIN")) {
                resp.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }

        // chặn /orders nếu chưa login
        if (uri.startsWith(req.getContextPath() + "/orders")) {
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect(req.getContextPath() + "/auth");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
