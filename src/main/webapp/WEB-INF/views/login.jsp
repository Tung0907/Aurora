<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<div class="row justify-content-center">
  <div class="col-md-4">
    <h2 class="mb-3">🔑 Đăng nhập</h2>
    <form method="post" action="auth">
      <input type="hidden" name="action" value="login"/>
      <div class="mb-3">
        <label>Email</label>
        <input type="email" name="email" class="form-control" required/>
      </div>
      <div class="mb-3">
        <label>Mật khẩu</label>
        <input type="password" name="password" class="form-control" required/>
      </div>
      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>
      <button class="btn btn-primary w-100">Đăng nhập</button>
      <p class="mt-3 text-center"><a href="auth?action=register">Chưa có tài khoản? Đăng ký</a></p>
    </form>
  </div>
</div>
<%@ include file="layout/footer.jsp" %>
