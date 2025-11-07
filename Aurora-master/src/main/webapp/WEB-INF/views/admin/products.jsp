<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="d-flex justify-content-between mb-3">
    <h2 class="text-gradient">📦 Quản lý sản phẩm</h2>
    <a href="admin/products?action=create" class="btn btn-primary">
        <i class="fa fa-plus"></i> Thêm sản phẩm
    </a>
</div>
<table class="table table-bordered shadow">
    <thead class="text-white" style="background:linear-gradient(90deg,#6a11cb,#2575fc);">
    <tr><th>ID</th><th>Tên</th><th>Giá</th><th>Danh mục</th><th>Hình ảnh</th><th>Hành động</th></tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.category.name}</td>
            <td><img src="${p.image}" width="60"></td>
            <td>
                <a href="admin/products?action=edit&id=${p.id}" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i></a>
                <a href="admin/products?action=delete&id=${p.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="../layout/footer.jsp" %>
