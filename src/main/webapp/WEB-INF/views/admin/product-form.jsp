<%@ include file="../layout/header.jsp" %>
<div class="card shadow p-4">
  <h3 class="text-gradient mb-3"><c:if test="${empty product}">Thêm</c:if><c:if test="${not empty product}">Sửa</c:if> sản phẩm</h3>
  <form action="admin/products" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <div class="mb-3">
      <label class="form-label">Tên sản phẩm</label>
      <input type="text" name="name" class="form-control" value="${product.name}">
    </div>
    <div class="mb-3">
      <label class="form-label">Giá</label>
      <input type="number" name="price" class="form-control" value="${product.price}">
    </div>
    <div class="mb-3">
      <label class="form-label">Hình ảnh (URL)</label>
      <input type="text" name="image" class="form-control" value="${product.image}">
    </div>
    <div class="mb-3">
      <label class="form-label">Danh mục</label>
      <select name="categoryId" class="form-select">
        <c:forEach var="c" items="${categories}">
          <option value="${c.id}" <c:if test="${product.category.id == c.id}">selected</c:if>>${c.name}</option>
        </c:forEach>
      </select>
    </div>
    <button class="btn btn-primary" type="submit">Lưu 💾</button>
    <a href="admin/products" class="btn btn-secondary">Hủy</a>
  </form>
</div>
<%@ include file="../layout/footer.jsp" %>
