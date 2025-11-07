<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<h2 class="mb-4 text-gradient">💳 Thanh toán</h2>
<form action="checkout" method="post" class="row g-3">
  <div class="col-md-6">
    <label class="form-label">Họ tên</label>
    <input type="text" name="name" class="form-control" value="${customer != null ? customer.name : ''}" required>
  </div>
  <div class="col-md-6">
    <label class="form-label">Số điện thoại</label>
    <input type="text" name="phone" class="form-control" value="${customer != null ? customer.phone : ''}" required>
  </div>
  <div class="col-12">
    <label class="form-label">Địa chỉ</label>
    <textarea name="address" class="form-control" rows="3">${customer != null ? customer.address : ''}</textarea>
  </div>
  <div class="col-12">
    <button type="submit" class="btn btn-primary">Xác nhận đặt hàng</button>
  </div>
</form>
<%@ include file="layout/footer.jsp" %>
