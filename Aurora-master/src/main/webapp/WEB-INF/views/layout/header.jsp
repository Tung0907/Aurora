<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Aurora Jewelry</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style>
    body { background: #f8f9fa; }
    .navbar {
      background: linear-gradient(90deg, #6a11cb, #2575fc);
    }
    .navbar-brand {
      font-weight: bold;
      color: white !important;
      font-size: 1.4rem;
    }
    .nav-link {
      color: white !important;
      margin-right: 15px;
      transition: 0.3s;
    }
    .nav-link:hover { color: #ffd700 !important; }
    .btn-primary { background: #6a11cb; border: none; }
    .btn-primary:hover { background: #2575fc; }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark shadow">
  <div class="container">
    <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/home">
      <img src="${pageContext.request.contextPath}/images/Logo1.png"
           alt="Aurora Logo" style="height:40px; margin-right:8px;">
      Aurora Jewelry
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cart"><i class="fa-solid fa-cart-shopping"></i> Giỏ hàng</a></li>

        <c:choose>
          <c:when test="${not empty sessionScope.user}">
            <li class="nav-item">
              <a class="nav-link" href="#">Xin chào, ${sessionScope.user.name}</a>
            </li>

            <!-- Nếu là Admin -->
            <c:if test="${sessionScope.role eq 'ADMIN'}">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/products">Sản phẩm</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/categories">Danh mục</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">Quản trị</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/orders">Quản lý đơn hàng</a>
              </li>
            </c:if>

            <!-- Nếu là User -->
            <c:if test="${sessionScope.role eq 'USER'}">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/orders">Đơn hàng của tôi</a>
              </li>
            </c:if>

            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/auth?action=logout">Đăng xuất</a>
            </li>
          </c:when>

          <c:otherwise>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/auth?action=login">Đăng nhập</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/auth?action=register">Đăng ký</a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>
