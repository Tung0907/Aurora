<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
  <div class="row justify-content-center">
    <div class="col-md-4">
      <div class="card shadow-sm">
        <div class="card-body p-4">
          <h2 class="mb-4 text-center">🔑 Đăng nhập</h2>
          
          <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
              <strong>Lỗi!</strong> ${error}
              <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
          </c:if>
          
          <c:if test="${not empty success}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
              <strong>Thành công!</strong> ${success}
              <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
          </c:if>
          
          <form method="post" action="${pageContext.request.contextPath}/auth">
            <input type="hidden" name="action" value="login"/>
            <div class="mb-3">
              <label class="form-label fw-bold">Email</label>
              <input type="email" name="email" class="form-control" 
                     placeholder="Nhập email của bạn" required/>
            </div>
            <div class="mb-3">
              <label class="form-label fw-bold">Mật khẩu</label>
              <input type="password" name="password" class="form-control" 
                     placeholder="Nhập mật khẩu" required/>
            </div>
            <button type="submit" class="btn btn-primary w-100 btn-lg">
              <i class="fas fa-sign-in-alt"></i> Đăng nhập
            </button>
            <p class="mt-3 text-center">
              Chưa có tài khoản? 
              <a href="${pageContext.request.contextPath}/auth?action=register" class="text-primary">
                Đăng ký ngay
              </a>
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="../layout/footer.jsp" %>
