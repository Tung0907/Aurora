<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
  <h2>Chi tiết đơn hàng</h2>
  <table class="table table-striped">
    <thead>
    <tr><th>ID</th><th>Order ID</th><th>Product ID</th><th>Số lượng</th><th>Giá</th></tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${details}">
      <tr>
        <td>${d.orderDetailId}</td>
        <td>${d.orderId}</td>
        <td>${d.productId}</td>
        <td>${d.quantity}</td>
        <td>${d.price}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../layout/footer.jsp" %>
