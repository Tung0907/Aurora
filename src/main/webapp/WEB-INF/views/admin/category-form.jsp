<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="card shadow p-4">
  <h3 class="text-gradient mb-3"><c:if test="${empty category}">Thêm</c:if><c:if test="${not empty category}">Sửa</c:if> danh mục</h3>
  <form action="${pageContext.request.contextPath}/categories" method="post">
    <input type="hidden" name="id" value="${category != null ? category.id : ''}">
    <div class="mb-3">
      <label class="form-label">Tên danh mục</label>
      <input type="text" name="name" class="form-control" value="${category != null ? category.name : ''}" required>
    </div>
    <button class="btn btn-primary" type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/categories" class="btn btn-secondary">Hủy</a>
  </form>
</div>
<%@ include file="../layout/footer.jsp" %>
