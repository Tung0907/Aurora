<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
    <h2 class="mb-3 fw-bold">Danh sách sản phẩm</h2>
    <a class="btn btn-primary mb-3" href="${pageContext.request.contextPath}/products?action=new">
        ➕ Thêm sản phẩm
    </a>

    <table class="table table-hover align-middle text-center shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>Ảnh</th>
            <th>ID</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Danh mục</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}">
            <tr>
                <!-- Ảnh sản phẩm -->
                <td>
                    <c:choose>
                        <c:when test="${not empty p.image}">
                            <img src="${pageContext.request.contextPath}/images/${p.image}"
                                 alt="${p.name}"
                                 style="width:80px;height:80px;object-fit:cover;border-radius:6px"/>
                        </c:when>
                        <c:otherwise>
                            <span class="text-muted fst-italic">Không có ảnh</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>${p.id}</td>
                <td>${p.name}</td>

                <!-- 💰 Giá tiền định dạng chuẩn VNĐ -->
                <td>
                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="₫" groupingUsed="true"/>
                </td>

                <td>${p.stock}</td>
                <td>${p.categoryId}</td>

                <td>
                    <a class="btn btn-sm btn-warning"
                       href="${pageContext.request.contextPath}/products?action=edit&id=${p.id}">
                        ✏ Edit
                    </a>
                    <a class="btn btn-sm btn-danger"
                       href="${pageContext.request.contextPath}/products?action=delete&id=${p.id}"
                       onclick="return confirm('Bạn có chắc muốn xóa?')">
                        🗑 Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty products}">
        <div class="text-center text-muted">Chưa có sản phẩm nào.</div>
    </c:if>
</div>

<%@ include file="../layout/footer.jsp" %>
