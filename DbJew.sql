-- Chạy trên SQL Server
CREATE DATABASE Jewelry;
GO

USE Jewelry;
GO

-- Xóa bảng nếu đã tồn tại (theo thứ tự foreign key)
IF OBJECT_ID('dbo.OrderDetail', 'U') IS NOT NULL DROP TABLE dbo.OrderDetail;
IF OBJECT_ID('dbo.[Order]', 'U') IS NOT NULL DROP TABLE dbo.[Order];
IF OBJECT_ID('dbo.Product', 'U') IS NOT NULL DROP TABLE dbo.Product;
IF OBJECT_ID('dbo.Category', 'U') IS NOT NULL DROP TABLE dbo.Category;
IF OBJECT_ID('dbo.Customer', 'U') IS NOT NULL DROP TABLE dbo.Customer;
GO

-- 1. Category
CREATE TABLE Category (
                          id INT IDENTITY(1,1) PRIMARY KEY,
                          name NVARCHAR(100) NOT NULL UNIQUE
);
GO

-- 2. Product
CREATE TABLE Product (
                         id INT IDENTITY(1,1) PRIMARY KEY,
                         name NVARCHAR(200) NOT NULL,
                         description NVARCHAR(1000) NULL,
                         price DECIMAL(18,2) NOT NULL,
                         stock INT NOT NULL DEFAULT 0,
                         image NVARCHAR(500) NULL,
                         categoryId INT NOT NULL,
                         CONSTRAINT FK_Product_Category FOREIGN KEY (categoryId) REFERENCES Category(id)
);
GO

-- 3. Customer (Đã cập nhật để khớp với form đăng ký)
CREATE TABLE Customer (
                          id INT IDENTITY(1,1) PRIMARY KEY,
                          name NVARCHAR(200) NOT NULL,         -- Giống 'Họ và tên'
                          email NVARCHAR(200) NOT NULL UNIQUE,  -- Giống 'Email' (Thêm UNIQUE)
                          phone NVARCHAR(10) NULL,            -- Giống 'Số điện thoại'
                          address NVARCHAR(200) NULL,         -- Giống 'Địa chỉ'

    -- Tăng độ dài cho mật khẩu đã mã hóa (hashed password)
                          password NVARCHAR(500) NOT NULL,    -- Giống 'Mật khẩu'

    -- Thêm cột 'role' mặc định là USER
                          role NVARCHAR(20) NOT NULL DEFAULT 'USER'
);
GO

-- 4. Order
CREATE TABLE [Order] (
                         id INT IDENTITY(1,1) PRIMARY KEY,
    customerId INT NULL,
    orderDate DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
    total DECIMAL(18,2) NOT NULL DEFAULT 0,
    CONSTRAINT FK_Order_Customer FOREIGN KEY (customerId) REFERENCES Customer(id)
    );
GO

-- 5. OrderDetail
CREATE TABLE OrderDetail (
                             id INT IDENTITY(1,1) PRIMARY KEY,
                             orderId INT NOT NULL,
                             productId INT NOT NULL,
                             quantity INT NOT NULL,
                             price DECIMAL(18,2) NOT NULL,
                             CONSTRAINT FK_OrderDetail_Order FOREIGN KEY (orderId) REFERENCES [Order](id),
                             CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (productId) REFERENCES Product(id)
);
GO

-- Thêm dữ liệu mẫu (Sản phẩm, Danh mục)
INSERT INTO Category (name) VALUES (N'Vòng tay'), (N'Vòng cổ'), (N'Nhẫn');
GO

INSERT INTO Product (name,description,price,stock,image,categoryId)
VALUES
(N'Vòng tay vàng 18K', N'Vòng tay cao cấp 18K', 2500000, 10, 'vt001.png', 1),
(N'Vòng cổ bạc ý', N'Vòng cổ phong cách Ý', 1500000, 5, 'vc001.jfif', 2),
(N'Nhẫn kim cương', N'Nhẫn vàng trắng đính kim cương', 12000000, 2, 'n001.jfif', 3);
GO

-- KHÔNG THÊM ADMIN BẰNG SQL NỮA (Xem giải thích bên dưới)x
-- INSERT INTO Customer (name, email, password, role) ... (KHÔNG DÙNG LỆNH NÀY)

-- Kiểm tra
SELECT * FROM Category;
SELECT * FROM Product;
SELECT * FROM Customer;
GO
UPDATE Customer
SET role = 'ADMIN'
WHERE email = 'admin@gmail.com';