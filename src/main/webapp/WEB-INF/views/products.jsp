<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<h2 class="mb-4 text-gradient">✨ Sản phẩm nổi bật</h2>
<div class="row">
  <c:forEach var="p" items="${products}">
    <div class="col-md-3 mb-4">
      <div class="card shadow-sm h-100">
        <img src="${p.image}" class="card-img-top" style="height:200px;object-fit:cover;">
        <div class="card-body">
          <h5 class="card-title">${p.name}</h5>
          <p class="card-text text-danger fw-bold">${p.price} VNĐ</p>
          <a href="${pageContext.request.contextPath}/cart?action=add&productId=${p.id}"
             class="btn btn-sm btn-primary">
            <i class="fa fa-cart-plus"></i> Thêm giỏ hàng
          </a>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
<%@ include file="layout/footer.jsp" %>
