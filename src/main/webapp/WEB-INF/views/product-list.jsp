<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="layout/header.jsp" %>

<div class="container mt-5">
  <h2 class="mb-4 text-gradient">💍 Danh sách sản phẩm</h2>
  <div class="row">
    <c:forEach var="p" items="${products}">
      <div class="col-md-3 mb-4">
        <div class="card shadow-sm h-100 border-0 rounded-3">
          <img src="images/${p.image}" class="card-img-top"
               style="height:200px;object-fit:cover;border-radius:10px 10px 0 0;">
          <div class="card-body text-center">
            <h5 class="card-title">${p.name}</h5>
            <p class="text-muted small">${p.description}</p>
            <p class="text-danger fw-bold">${p.price} VNĐ</p>
            <a href="${pageContext.request.contextPath}/cart?action=add&productId=${p.id}"
               class="btn btn-sm btn-primary">
              <i class="fa fa-cart-plus"></i> Thêm giỏ hàng
            </a>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<%@ include file="layout/footer.jsp" %>
