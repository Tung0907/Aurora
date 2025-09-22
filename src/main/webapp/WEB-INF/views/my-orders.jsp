<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Đơn hàng của tôi</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2>Đơn hàng của tôi</h2>

  <c:if test="${empty orders}">
    <div class="alert alert-info">Bạn chưa có đơn hàng nào.</div>
  </c:if>

  <c:if test="${not empty orders}">
    <table class="table table-bordered table-striped mt-3">
      <thead class="table-dark">
      <tr>
        <th>ID</th>
        <th>Ngày đặt</th>
        <th>Tổng tiền</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="o" items="${orders}">
        <tr>
          <td>${o.id}</td>
          <td>${o.orderDate}</td>
          <td>${o.total} đ</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>

  <a href="${pageContext.request.contextPath}/products" class="btn btn-primary">Tiếp tục mua sắm</a>
</div>
</body>
</html>
