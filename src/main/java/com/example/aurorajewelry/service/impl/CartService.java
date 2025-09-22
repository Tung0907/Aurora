package com.example.aurorajewelry.service.impl;

import com.example.aurorajewelry.model.Product;
import java.util.*;

public class CartService {
    // session-safe structure is Map<Integer(productId), CartItem>
    public static class Item {
        public Product product;
        public int quantity;
        public Item(Product p, int q) { product = p; quantity = q; }
    }

    // get cart map from session; create if absent
    @SuppressWarnings("unchecked")
    public static Map<Integer, Item> getCart(javax.servlet.http.HttpSession session) {
        Map<Integer, Item> cart = (Map<Integer, Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public static double getTotal(Map<Integer, Item> cart) {
        double total = 0;
        for (Item it : cart.values()) {
            total += it.product.getPrice() * it.quantity;
        }
        return total;
    }
}
