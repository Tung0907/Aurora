<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-5">
  <div class="card shadow-lg border-0 rounded-3">
    <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
      <h4 class="mb-0">👤 Danh sách khách hàng</h4>
      <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-sm btn-outline-light">⬅ Quay lại</a>
    </div>
    <div class="card-body p-4">
      <table class="table table-hover align-middle text-center">
        <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Tên khách hàng</th>
          <th>Email</th>
          <th>Số điện thoại</th>
          <th>Địa chỉ</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${customers}">
          <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.email}</td>
            <td>${c.phone}</td>
            <td>${c.address}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<%@ include file="../layout/footer.jsp" %>
