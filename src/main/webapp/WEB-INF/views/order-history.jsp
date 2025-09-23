<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lịch sử đơn hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4 text-success text-center">🛒 Lịch sử đơn hàng của bạn</h2>
    <div class="card shadow">
        <div class="card-body">
            <c:if test="${empty orders}">
                <p class="text-muted text-center">Bạn chưa có đơn hàng nào.</p>
            </c:if>
            <c:if test="${not empty orders}">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-success">
                    <tr>
                        <th>Mã đơn</th>
                        <th>Ngày đặt</th>
                        <th>Tổng tiền</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="o">
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.orderDate}</td>
                            <td><strong>${o.total}₫</strong></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
