<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="p-5 bg-white shadow rounded text-center">
    <h2 class="text-gradient">Bảng điều khiển quản trị</h2>
    <p class="lead">Xin chào Admin! Hãy quản lý cửa hàng của bạn.</p>
    <div class="row mt-4">
        <div class="col-md-3">
            <a href="admin/products" class="card shadow-sm text-decoration-none">
                <div class="card-body">
                    <i class="fa fa-box fa-2x text-gradient"></i>
                    <h5 class="mt-2">Sản phẩm</h5>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="admin/categories" class="card shadow-sm text-decoration-none">
                <div class="card-body">
                    <i class="fa fa-tags fa-2x text-gradient"></i>
                    <h5 class="mt-2">Danh mục</h5>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="admin/orders" class="card shadow-sm text-decoration-none">
                <div class="card-body">
                    <i class="fa fa-receipt fa-2x text-gradient"></i>
                    <h5 class="mt-2">Đơn hàng</h5>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="home" class="card shadow-sm text-decoration-none">
                <div class="card-body">
                    <i class="fa fa-home fa-2x text-gradient"></i>
                    <h5 class="mt-2">Trang khách</h5>
                </div>
            </a>
        </div>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>
