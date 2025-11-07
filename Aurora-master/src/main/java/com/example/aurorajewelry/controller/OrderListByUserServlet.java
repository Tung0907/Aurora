package com.example.aurorajewelry.controller;
import com.example.aurorajewelry.model.Order;
import com.example.aurorajewelry.repository.OrderRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderListByUserServlet", urlPatterns = {"/my-orders"})
public class OrderListByUserServlet extends HttpServlet {
    private OrderRepository orderRepo;

    @Override
    public void init() {
        orderRepo = new OrderRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer customerId = null;

        Object userObj = session.getAttribute("user");
        if (userObj != null) {
            try {
                java.lang.reflect.Method m = userObj.getClass().getMethod("getId");
                Object val = m.invoke(userObj);
                if (val instanceof Number) customerId = ((Number) val).intValue();
            } catch (Exception ignored) {}
        }

        if (customerId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<Order> orders = orderRepo.findByCustomerId(customerId);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/my-orders.jsp").forward(req, resp);
    }
}

