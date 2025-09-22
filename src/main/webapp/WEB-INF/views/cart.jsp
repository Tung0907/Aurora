<%@ include file="layout/header.jsp" %>
<h2 class="mb-4 text-gradient"><i class="fa fa-cart-shopping"></i> Giỏ hàng</h2>
<table class="table table-bordered shadow">
  <thead class="text-white" style="background:linear-gradient(90deg,#6a11cb,#2575fc);">
  <tr><th>Sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th><th></th></tr>
  </thead>
  <tbody>
  <c:forEach var="item" items="${cart}">
    <tr>
      <td>${item.product.name}</td>
      <td>${item.product.price}</td>
      <td>${item.quantity}</td>
      <td>${item.product.price * item.quantity}</td>
      <td><a href="cart?action=remove&id=${item.product.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="checkout" class="btn btn-success">Thanh toán</a>
<%@ include file="layout/footer.jsp" %>
