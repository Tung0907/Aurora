<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Aurora Jewelry - Trang chủ</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style>
    body { background: #f8f9fa; }
    .text-gradient {
      background: linear-gradient(90deg, #6a11cb, #2575fc);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }
    .card-title {
      font-size: 1.1rem;
      font-weight: bold;
    }
  </style>
</head>
<body>
<%@ include file="layout/header.jsp" %>

<!-- Banner -->
<div class="text-center p-5 bg-white shadow rounded mb-5">
  <img src="assets/images/necklace1.jfif" class="img-fluid rounded mb-3" alt="Aurora Banner" style="max-height: 350px; object-fit: cover;">
  <h1 class="text-gradient">✨ Chào mừng đến Aurora Jewelry ✨</h1>
  <p class="lead">Khám phá những sản phẩm trang sức tinh tế và sang trọng</p>
  <a href="products" class="btn btn-primary btn-lg"><i class="fa fa-gem"></i> Xem sản phẩm</a>
</div>

<!-- Sản phẩm nổi bật -->
<div class="container">
  <h2 class="mb-4 text-gradient"><i class="fa fa-star"></i> Sản phẩm nổi bật</h2>
  <div class="row">

    <!-- Ring demo -->
    <div class="col-md-3 mb-4">
      <div class="card shadow-sm h-100">
        <img src="assets/images/ring1.jfif" class="card-img-top" style="height:220px;object-fit:cover;" alt="Ring">
        <div class="card-body">
          <h5 class="card-title">Elegant Ring</h5>
          <p class="card-text text-danger fw-bold">1.200.000 VNĐ</p>
          <a href="cart?action=add&id=1" class="btn btn-sm btn-primary">
            <i class="fa fa-cart-plus"></i> Thêm giỏ hàng
          </a>
        </div>
      </div>
    </div>

    <!-- Necklace demo -->
    <div class="col-md-3 mb-4">
      <div class="card shadow-sm h-100">
        <img src="assets/images/necklace1.jfif" class="card-img-top" style="height:220px;object-fit:cover;" alt="Necklace">
        <div class="card-body">
          <h5 class="card-title">Luxury Necklace</h5>
          <p class="card-text text-danger fw-bold">2.500.000 VNĐ</p>
          <a href="cart?action=add&id=2" class="btn btn-sm btn-primary">
            <i class="fa fa-cart-plus"></i> Thêm giỏ hàng
          </a>
        </div>
      </div>
    </div>

    <!-- Nếu có dữ liệu động từ DB thì lặp thêm -->
    <c:forEach var="p" items="${products}">
      <div class="col-md-3 mb-4">
        <div class="card shadow-sm h-100">
          <img src="${p.image}" class="card-img-top" style="height:220px;object-fit:cover;" alt="${p.name}">
          <div class="card-body">
            <h5 class="card-title">${p.name}</h5>
            <p class="card-text text-danger fw-bold">${p.price} VNĐ</p>
            <a href="cart?action=add&id=${p.id}" class="btn btn-sm btn-primary">
              <i class="fa fa-cart-plus"></i> Thêm giỏ hàng
            </a>
          </div>
        </div>
      </div>
    </c:forEach>

  </div>
</div>

<%@ include file="layout/footer.jsp" %>
</body>
</html>
