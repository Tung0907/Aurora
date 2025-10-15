<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<%@ page isELIgnored="false" %>

<div class="container mt-4">
  <h3><c:if test="${empty product}">Thêm</c:if><c:if test="${not empty product}">Chỉnh sửa</c:if> sản phẩm</h3>
  <form action="${pageContext.request.contextPath}/admin/products" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${product != null ? product.id : ''}" />
    <div class="mb-3">
      <label class="form-label">Tên</label>
      <input type="text" name="name" class="form-control" value="${product != null ? product.name : ''}" required />
    </div>
    <div class="mb-3">
      <label class="form-label">Mô tả</label>
      <input type="text" name="description" class="form-control" value="${product != null ? product.description : ''}" />
    </div>
    <div class="mb-3">
      <label class="form-label">Giá</label>
      <input type="number" step="0.01" name="price" class="form-control" value="${product != null ? product.price : 0}" />
    </div>
    <div class="mb-3">
      <label class="form-label">Số lượng</label>
      <input type="number" name="stock" class="form-control" value="${product != null ? product.stock : 0}" />
    </div>
    <div class="mb-3">
      <label class="form-label">Ảnh (upload)</label>
      <input type="file" name="imageFile" class="form-control" />
      <small class="form-text text-muted">Hoặc nhập URL/ tên file hiện có:</small>
      <input type="text" name="image" class="form-control" value="${product != null ? product.image : ''}" />
      <c:if test="${not empty product and product.image != null}">
        <div class="mt-2">
          <img src="${pageContext.request.contextPath}/images/${product.image}"
               alt="product" style="max-width:140px;"/>
        </div>
      </c:if>
    </div>

    <div class="mb-3">
      <label class="form-label">Danh mục</label>
      <select name="categoryId" class="form-select">
        <c:forEach var="c" items="${categories}">
          <option value="${c.id}" <c:if test="${product != null && product.categoryId == c.id}">selected</c:if>>
              ${c.name}
          </option>
        </c:forEach>
      </select>
    </div>

    <button class="btn btn-primary" type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-secondary">Hủy</a>
  </form>
</div>

<%@ include file="../layout/footer.jsp" %>
