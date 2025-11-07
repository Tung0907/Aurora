<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<h2 class="text-gradient mb-3">🧾 Quản lý đơn hàng</h2>
<table class="table table-bordered shadow">
  <thead class="text-white" style="background:linear-gradient(90deg,#6a11cb,#2575fc);">
  <tr><th>ID</th><th>Khách hàng</th><th>Ngày đặt</th><th>Tổng tiền</th><th>Trạng thái</th><th>Hành động</th></tr>
  </thead>
  <tbody>
  <c:forEach var="o" items="${orders}">
    <tr>
      <td>${o.id}</td>
      <td>${o.customerName}</td>
      <td>${o.orderDate}</td>
      <td>${o.total} VNĐ</td>
      <td>${o.status}</td>
      <td>
        <a href="admin/orders?action=detail&id=${o.id}" class="btn btn-sm btn-info"><i class="fa fa-eye"></i></a>
        <a href="admin/orders?action=delete&id=${o.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<%@ include file="../layout/footer.jsp" %>
