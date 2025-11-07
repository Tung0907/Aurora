<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../layout/header.jsp" %>
<%@ page isELIgnored="false" %>

<div class="container mt-4">
  <h3 class="mb-4">
    <c:if test="${empty order}">➕ Thêm</c:if>
    <c:if test="${not empty order}">✏ Chỉnh sửa</c:if> đơn hàng
  </h3>
  
  <form action="${pageContext.request.contextPath}/admin/orders" method="post">
    <input type="hidden" name="action" value="save" />
    <input type="hidden" name="id" value="${order != null ? order.id : ''}" />
    
    <div class="card shadow-sm">
      <div class="card-body">
        <div class="mb-3">
          <label class="form-label fw-bold">Khách hàng <span class="text-danger">*</span></label>
          <select name="customerId" class="form-select" required>
            <option value="">-- Chọn khách hàng --</option>
            <c:forEach var="c" items="${customers}">
              <option value="${c.id}" 
                <c:if test="${order != null && order.customerId != null && order.customerId == c.id}">selected</c:if>>
                ${c.name} (${c.email})
              </option>
            </c:forEach>
          </select>
          <small class="form-text text-muted">Chọn khách hàng cho đơn hàng này</small>
        </div>

        <div class="mb-3">
          <label class="form-label fw-bold">Ngày đặt hàng <span class="text-danger">*</span></label>
          <input type="datetime-local" name="orderDate" class="form-control" 
                 value="<c:if test="${order != null && order.orderDate != null}">
                   <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
                 </c:if>
                 <c:if test="${empty order}">
                   <fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd'T'HH:mm"/>
                 </c:if>" 
                 required />
        </div>

        <div class="mb-3">
          <label class="form-label fw-bold">Tổng tiền (VNĐ) <span class="text-danger">*</span></label>
          <input type="number" step="0.01" name="total" class="form-control" 
                 value="${order != null ? order.total : 0}" 
                 min="0" required />
          <small class="form-text text-muted">Nhập tổng tiền của đơn hàng</small>
        </div>

        <div class="mt-4">
          <button class="btn btn-primary" type="submit">
            💾 Lưu đơn hàng
          </button>
          <a href="${pageContext.request.contextPath}/admin/orders" class="btn btn-secondary">
            ❌ Hủy
          </a>
        </div>
      </div>
    </div>
  </form>
</div>

<%@ include file="../layout/footer.jsp" %>


