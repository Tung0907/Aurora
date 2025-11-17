<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Giỏ hàng</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f8f8f8;
    }

    h2 {
      color: #333;
      text-align: center;
    }

    table {
      border-collapse: collapse;
      width: 80%;
      margin: 20px auto;
      background: white;
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    table th, table td {
      padding: 12px;
      text-align: center;
      border: 1px solid #ddd;
    }

    table th {
      background-color: #f2f2f2;
      font-weight: bold;
    }

    button {
      padding: 6px 12px;
      border: none;
      background-color: #007bff;
      color: white;
      border-radius: 4px;
      cursor: pointer;
    }

    button:hover {
      background-color: #0056b3;
    }

    a {
      text-decoration: none;
      color: #007bff;
      margin-top: 20px;
      display: inline-block;
    }

    a:hover {
      text-decoration: underline;
    }

    .actions {
      text-align: center;
      margin-top: 20px;
    }
  </style>
</head>
<body>
<h2>🛒 Giỏ hàng</h2>

<c:if test="${empty cart}">
  <p style="text-align:center;">Giỏ hàng trống.</p>
  <div class="actions">
    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">⬅ Tiếp tục mua sắm</a>
  </div>
</c:if>

<c:if test="${not empty cart}">
  <table>
    <tr>
      <th>Tên sản phẩm</th>
      <th>Giá</th>
      <th>Số lượng</th>
      <th>Thành tiền</th>
      <th>Thao tác</th>
    </tr>
    <c:forEach var="entry" items="${cart}">
      <c:set var="item" value="${entry.value}"/>
      <tr>
        <td>${item.product.name}</td>
        <td>${item.product.price}</td>
        <td>
          <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="productId" value="${item.product.id}"/>
            <input type="number" name="quantity" value="${item.quantity}" min="1" style="width:60px;"/>
            <button type="submit">Cập nhật</button>
          </form>
        </td>
        <td>${item.product.price * item.quantity}</td>
        <td>
          <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
            <input type="hidden" name="action" value="remove"/>
            <input type="hidden" name="productId" value="${item.product.id}"/>
            <button type="submit" style="background-color:#dc3545;">Xóa</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    <tr>
      <td colspan="3" align="right"><b>Tổng cộng:</b></td>
      <td colspan="2"><b>${total}</b></td>
    </tr>
  </table>

  <div class="actions">
    <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
      <input type="hidden" name="action" value="clear"/>
      <button type="submit" style="background-color:#dc3545;">Xóa toàn bộ giỏ hàng</button>
    </form>

    <a href="${pageContext.request.contextPath}/checkout" style="margin-left:20px;">💳 Thanh toán</a>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">⬅ Tiếp tục mua sắm</a>
  </div>
</c:if>

</body>
</html>
