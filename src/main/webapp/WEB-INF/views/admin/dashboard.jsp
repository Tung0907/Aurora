<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
    <h2 class="fw-bold mb-4">📊 Bảng điều khiển Admin</h2>

    <!-- Thống kê nhanh -->
    <div class="row text-center mb-4">
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4>${fn:length(products)}</h4>
                    <p class="text-muted">Sản phẩm</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4>${fn:length(categories)}</h4>
                    <p class="text-muted">Danh mục</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4>${fn:length(orders)}</h4>
                    <p class="text-muted">Đơn hàng</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4>${fn:length(customers)}</h4>
                    <p class="text-muted">Khách hàng</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Menu quản trị -->
    <div class="list-group shadow-sm">
        <a href="${pageContext.request.contextPath}/products" class="list-group-item list-group-item-action">
            🛍️ Quản lý sản phẩm
        </a>
        <a href="${pageContext.request.contextPath}/categories" class="list-group-item list-group-item-action">
            📂 Quản lý danh mục
        </a>
        <a href="${pageContext.request.contextPath}/orders" class="list-group-item list-group-item-action">
            📦 Quản lý đơn hàng
        </a>
        <a href="${pageContext.request.contextPath}/customers" class="list-group-item list-group-item-action">
            👤 Quản lý khách hàng
        </a>
    </div>
</div>

<%@ include file="../layout/footer.jsp" %>
