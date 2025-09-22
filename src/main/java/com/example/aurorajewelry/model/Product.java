package com.example.aurorajewelry.model;

public class Product {
    private int productId;       // PK
    private String productCode;  // Mã sản phẩm
    private String name;         // Tên sản phẩm
    private String material;     // Chất liệu
    private String size;         // Kích thước
    private double price;        // Giá bán
    private int stock;           // Tồn kho
    private boolean active;      // Còn kinh doanh
    private String image;        // Ảnh
    private int categoryId;      // FK

    public Product() {}

    public Product(int productId, String productCode, String name, String material,
                   String size, double price, int stock, boolean active,
                   String image, int categoryId) {
        this.productId = productId;
        this.productCode = productCode;
        this.name = name;
        this.material = material;
        this.size = size;
        this.price = price;
        this.stock = stock;
        this.active = active;
        this.image = image;
        this.categoryId = categoryId;
    }

    // Getter & Setter
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
