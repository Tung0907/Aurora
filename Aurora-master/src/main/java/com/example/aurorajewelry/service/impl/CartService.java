package com.example.aurorajewelry.service.impl;

import com.example.aurorajewelry.model.Product;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class CartService {

    public static class Item {
        private Product product;
        private int quantity;

        public Item(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        // Getter + Setter để JSP EL truy cập
        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // Lấy cart từ session
    @SuppressWarnings("unchecked")
    public static Map<Integer, Item> getCart(HttpSession session) {
        Map<Integer, Item> cart = (Map<Integer, Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    // Tính tổng tiền
    public static double getTotal(Map<Integer, Item> cart) {
        double total = 0;
        for (Item it : cart.values()) {
            total += it.getProduct().getPrice() * it.getQuantity();
        }
        return total;
    }
}
