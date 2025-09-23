package com.example.aurorajewelry.controller;

import com.example.aurorajewelry.model.Product;
import com.example.aurorajewelry.service.ProductService;
import com.example.aurorajewelry.service.impl.ProductServiceImpl;
import com.example.aurorajewelry.service.CategoryService;
import com.example.aurorajewelry.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ProductServlet", urlPatterns = {"/admin/products"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 5 * 1024 * 1024,    // 5MB
        maxRequestSize = 10 * 1024 * 1024
)
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() {
        productService = new ProductServiceImpl();
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.setAttribute("categories", categoryService.getAll());
                req.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Product p = productService.getById(id);
                req.setAttribute("product", p);
                req.setAttribute("categories", categoryService.getAll());
                req.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(req, resp);
                break;
            case "delete":
                productService.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/admin/products");
                break;
            default:
                List<Product> list = productService.getAll();
                System.out.println("Sản phẩm lấy được: " + list.size());
                req.setAttribute("products", list);
                req.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(req, resp);
                break;
        }
    }

    private String saveUploadedFile(Part filePart, HttpServletRequest req) throws IOException {
        if (filePart == null || filePart.getSize() == 0) return null;

        String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String ext = "";
        int i = submitted.lastIndexOf('.');
        if (i > 0) ext = submitted.substring(i);
        String filename = UUID.randomUUID().toString() + ext;

        String imagesDir = getServletContext().getRealPath("") + "images";
        File dir = new File(imagesDir);
        if (!dir.exists()) dir.mkdirs();

        Path filePath = Paths.get(imagesDir, filename);
        try (InputStream is = filePart.getInputStream()) {
            Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return filename;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String idStr = req.getParameter("id");
        int id = (idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr);

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        double price = 0;
        try { price = Double.parseDouble(req.getParameter("price")); } catch (Exception ignored) {}

        int stock = 0;
        try { stock = Integer.parseInt(req.getParameter("stock")); } catch (Exception ignored) {}

        int catId = 0;
        try { catId = Integer.parseInt(req.getParameter("categoryId")); } catch (Exception ignored) {}

        // Xử lý upload ảnh
        Part filePart = req.getPart("imageFile");
        String uploadedFileName = saveUploadedFile(filePart, req);
        String imageParam = req.getParameter("image");
        String finalImage = (uploadedFileName != null) ? uploadedFileName :
                (imageParam != null ? imageParam : null);

        // Tạo Product đúng với model
        Product p = new Product(
                id,
                name,
                description,
                price,
                stock,
                finalImage,
                catId
        );

        if (id == 0) {
            productService.add(p);
        } else {
            productService.update(p);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }
}
