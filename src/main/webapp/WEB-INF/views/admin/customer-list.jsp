<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
  <h2>Danh sách khách hàng</h2>
  <table class="table table-striped">
    <thead>
    <tr><th>ID</th><th>Tên</th><th>Email</th></tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${customers}">
      <tr>
        <td>${c.customerId}</td>
        <td>${c.name}</td>
        <td>${c.email}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../layout/footer.jsp" %>
