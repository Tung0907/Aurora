<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
    <h2>Danh sách sản phẩm</h2>
    <a class="btn btn-primary mb-3" href="${pageContext.request.contextPath}/products?action=new">Thêm sản phẩm</a>
    <table class="table table-striped">
        <thead>
        <tr><th>Ảnh</th><th>ID</th><th>Tên</th><th>Giá</th><th>Số lượng</th><th>Danh mục</th><th>Hành động</th></tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}">
            <img src="${pageContext.request.contextPath}/images/${product.image}" alt="${product.name}" style="max-width:150px;">
            <tr>
                <td>
                    <c:if test="${not empty p.image}">
                        <img src="${pageContext.request.contextPath}/resources/images/${p.image}" alt="img" style="width:80px;height:80px;object-fit:cover"/>
                    </c:if>
                </td>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.stock}</td>
                <td>${p.categoryId}</td>
                <td>
                    <a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/products?action=edit&id=${p.id}">Edit</a>
                    <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/products?action=delete&id=${p.id}" onclick="return confirm('Delete?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../layout/footer.jsp" %>
