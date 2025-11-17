<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
    <h2 class="mb-3 fw-bold">📦 Quản lý đơn hàng</h2>
    <a class="btn btn-primary mb-3" href="${pageContext.request.contextPath}/admin/orders?action=create">
        ➕ Thêm đơn hàng mới
    </a>

    <table class="table table-hover align-middle text-center shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>Mã đơn</th>
            <th>Khách hàng</th>
            <th>Ngày đặt</th>
            <th>Tổng tiền</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="o" items="${orders}">
            <tr>
                <td>${o.id}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty o.customerName}">
                            ${o.customerName}
                        </c:when>
                        <c:otherwise>
                            <span class="text-muted">Khách vãng lai</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                </td>
                <td>
                    <strong>
                        <fmt:formatNumber value="${o.total}" type="currency" currencySymbol="₫" groupingUsed="true"/>
                    </strong>
                </td>
                <td>
                    <a class="btn btn-sm btn-info me-1"
                       href="${pageContext.request.contextPath}/admin/orders?action=detail&id=${o.id}"
                       title="Xem chi tiết">
                        👁 Chi tiết
                    </a>
                    <a class="btn btn-sm btn-warning me-1"
                       href="${pageContext.request.contextPath}/admin/orders?action=edit&id=${o.id}"
                       title="Sửa">
                        ✏ Sửa
                    </a>
                    <a class="btn btn-sm btn-danger"
                       href="${pageContext.request.contextPath}/admin/orders?action=delete&id=${o.id}"
                       onclick="return confirm('Bạn có chắc muốn xóa đơn hàng này?')"
                       title="Xóa">
                        🗑 Xóa
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty orders}">
        <div class="text-center text-muted mt-4">Chưa có đơn hàng nào.</div>
    </c:if>
</div>

<%@ include file="../layout/footer.jsp" %>
