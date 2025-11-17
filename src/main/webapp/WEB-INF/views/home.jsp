<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-4">

  <!-- Banner -->
  <div class="row">
    <div class="col-12">
      <div class="card border-0 shadow-sm">
        <!-- ⚠️ Đảm bảo đúng tên ảnh trong webapp/images -->
        <img src="${pageContext.request.contextPath}/images/banner1.png"
             class="card-img-top"
             alt="Aurora Banner" />
      </div>
    </div>
  </div>

  <!-- Sản phẩm nổi bật -->
  <div class="row mt-5">
    <div class="col-12 text-center">
      <h2 class="fw-bold mb-4">✨ Sản phẩm nổi bật ✨</h2>
    </div>

    <c:forEach var="p" items="${products}">
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm border-0">
          <!-- Load ảnh sản phẩm -->
          <img src="${pageContext.request.contextPath}/images/${p.image}"
               class="card-img-top"
               alt="${p.name}" />
          <div class="card-body text-center">
            <h5 class="card-title">${p.name}</h5>
            <p class="card-text text-muted">
              <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> VNĐ
            </p>
            <a href="${pageContext.request.contextPath}/cart?action=add&productId=${p.id}"
               class="btn btn-sm btn-primary">
              <i class="fa fa-cart-plus"></i> Thêm vào giỏ
            </a>
          </div>
        </div>
      </div>
    </c:forEach>

    <!-- Nếu không có sản phẩm -->
    <c:if test="${empty products}">
      <div class="col-12 text-center text-muted">
        <p>Chưa có sản phẩm nào.</p>
      </div>
    </c:if>

  </div>
</div>

<footer class="bg-dark text-white text-center py-3 mt-4">
  <p>&copy; 2025 Aurora Jewelry. All rights reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
