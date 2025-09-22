<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="layout/header.jsp" %>

<h2 class="mb-4 text-gradient"><i class="fa fa-cart-shopping"></i> Giỏ hàng</h2>
<table class="table table-bordered shadow">
  <thead class="text-white" style="background:linear-gradient(90deg,#6a11cb,#2575fc);">
  <tr><th>Sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th><th></th></tr>
  </thead>
  <tbody>
  <c:forEach var="entry" items="${cart}">
    <c:set var="item" value="${entry.value}" />
    <tr>
      <td>${item.product.name}</td>
      <td><fmt:formatNumber value="${item.product.price}" type="number" groupingUsed="true"/> VNĐ</td>
      <td>${item.quantity}</td>
      <td><fmt:formatNumber value="${item.product.price * item.quantity}" type="number" groupingUsed="true"/> VNĐ</td>
      <td>
        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline">
          <input type="hidden" name="action" value="remove"/>
          <input type="hidden" name="productId" value="${item.product.id}"/>
          <button class="btn btn-sm btn-danger" type="submit"><i class="fa fa-trash"></i></button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
  <tfoot>
  <tr>
    <td colspan="3" class="text-end fw-bold">Tổng cộng</td>
    <td colspan="2" class="fw-bold"><fmt:formatNumber value="${total}" type="number" groupingUsed="true"/> VNĐ</td>
  </tr>
  </tfoot>
</table>
<a href="${pageContext.request.contextPath}/checkout" class="btn btn-success">Thanh toán</a>
<%@ include file="layout/footer.jsp" %>
