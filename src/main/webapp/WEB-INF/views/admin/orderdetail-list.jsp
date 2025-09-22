<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Order Details</title></head>
<body>
<h2>Order Details</h2>
<table border="1">
  <thead>
  <tr><th>ID</th><th>Order ID</th><th>Product ID</th><th>Quantity</th><th>Price</th></tr>
  </thead>
  <tbody>
  <c:forEach var="d" items="${details}">
    <tr>
      <td>${d.id}</td>
      <td>${d.orderId}</td>
      <td>${d.productId}</td>
      <td>${d.quantity}</td>
      <td>${d.price}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
