<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
  <h2 class="mb-4">📋 Chi tiết đơn hàng #${order.id}</h2>
  
  <div class="row">
    <!-- Thông tin đơn hàng -->
    <div class="col-md-6 mb-4">
      <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
          <h5 class="mb-0">ℹ️ Thông tin đơn hàng</h5>
        </div>
        <div class="card-body">
          <table class="table table-borderless">
            <tr>
              <td class="fw-bold">Mã đơn hàng:</td>
              <td>#${order.id}</td>
            </tr>
            <tr>
              <td class="fw-bold">Khách hàng:</td>
              <td>
                <c:choose>
                  <c:when test="${not empty customer}">
                    ${customer.name} (${customer.email})
                  </c:when>
                  <c:otherwise>
                    <span class="text-muted">Khách vãng lai</span>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <td class="fw-bold">Ngày đặt:</td>
              <td>
                <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm:ss"/>
              </td>
            </tr>
            <tr>
              <td class="fw-bold">Tổng tiền:</td>
              <td>
                <strong class="text-danger fs-5">
                  <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="₫" groupingUsed="true"/>
                </strong>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>

    <!-- Thông tin khách hàng -->
    <c:if test="${not empty customer}">
      <div class="col-md-6 mb-4">
        <div class="card shadow-sm">
          <div class="card-header bg-info text-white">
            <h5 class="mb-0">👤 Thông tin khách hàng</h5>
          </div>
          <div class="card-body">
            <table class="table table-borderless">
              <tr>
                <td class="fw-bold">Tên:</td>
                <td>${customer.name}</td>
              </tr>
              <tr>
                <td class="fw-bold">Email:</td>
                <td>${customer.email}</td>
              </tr>
              <c:if test="${not empty customer.phone}">
                <tr>
                  <td class="fw-bold">Số điện thoại:</td>
                  <td>${customer.phone}</td>
                </tr>
              </c:if>
              <c:if test="${not empty customer.address}">
                <tr>
                  <td class="fw-bold">Địa chỉ:</td>
                  <td>${customer.address}</td>
                </tr>
              </c:if>
            </table>
          </div>
        </div>
      </div>
    </c:if>
  </div>

  <!-- Chi tiết sản phẩm -->
  <div class="card shadow-sm">
    <div class="card-header bg-success text-white">
      <h5 class="mb-0">🛍️ Chi tiết sản phẩm</h5>
    </div>
    <div class="card-body">
      <c:choose>
        <c:when test="${not empty details}">
          <table class="table table-hover align-middle">
            <thead class="table-light">
              <tr>
                <th>STT</th>
                <th>Sản phẩm</th>
                <th class="text-center">Số lượng</th>
                <th class="text-end">Đơn giá</th>
                <th class="text-end">Thành tiền</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="detail" items="${details}" varStatus="status">
                <c:set var="product" value="${null}"/>
                <c:forEach var="p" items="${products}">
                  <c:if test="${p.id == detail.productId}">
                    <c:set var="product" value="${p}"/>
                  </c:if>
                </c:forEach>
                <tr>
                  <td>${status.index + 1}</td>
                  <td>
                    <c:choose>
                      <c:when test="${not empty product}">
                        <div class="d-flex align-items-center">
                          <c:if test="${not empty product.image}">
                            <img src="${pageContext.request.contextPath}/images/${product.image}"
                                 alt="${product.name}"
                                 style="width:60px;height:60px;object-fit:cover;border-radius:4px;margin-right:10px;"/>
                          </c:if>
                          <div>
                            <strong>${product.name}</strong>
                            <br/>
                            <small class="text-muted">Mã SP: #${product.id}</small>
                          </div>
                        </div>
                      </c:when>
                      <c:otherwise>
                        <span class="text-muted">Sản phẩm #${detail.productId} (đã xóa)</span>
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td class="text-center">
                    <span class="badge bg-secondary">${detail.quantity}</span>
                  </td>
                  <td class="text-end">
                    <fmt:formatNumber value="${detail.price}" type="currency" currencySymbol="₫" groupingUsed="true"/>
                  </td>
                  <td class="text-end">
                    <strong>
                      <fmt:formatNumber value="${detail.price * detail.quantity}" type="currency" currencySymbol="₫" groupingUsed="true"/>
                    </strong>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
            <tfoot class="table-light">
              <tr>
                <td colspan="4" class="text-end fw-bold">Tổng cộng:</td>
                <td class="text-end">
                  <strong class="text-danger fs-5">
                    <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="₫" groupingUsed="true"/>
                  </strong>
                </td>
              </tr>
            </tfoot>
          </table>
        </c:when>
        <c:otherwise>
          <div class="text-center text-muted py-4">
            <p>Đơn hàng này chưa có sản phẩm nào.</p>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <div class="mt-4">
    <a href="${pageContext.request.contextPath}/admin/orders" class="btn btn-secondary">
      ← Quay lại danh sách
    </a>
    <a href="${pageContext.request.contextPath}/admin/orders?action=edit&id=${order.id}" class="btn btn-warning">
      ✏ Sửa đơn hàng
    </a>
  </div>
</div>

<%@ include file="../layout/footer.jsp" %>


