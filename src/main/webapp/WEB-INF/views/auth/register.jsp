<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h2 class="mb-4 text-center">📝 Đăng ký tài khoản</h2>
                    
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
                    
                    <form method="post" action="${pageContext.request.contextPath}/auth" id="registerForm">
                        <input type="hidden" name="action" value="register"/>
                        
                        <div class="mb-3">
                            <label class="form-label fw-bold">Họ và tên <span class="text-danger">*</span></label>
                            <input type="text" name="name" class="form-control" 
                                   placeholder="Nhập họ và tên" required 
                                   minlength="2" maxlength="100"/>
                            <small class="form-text text-muted">Tối thiểu 2 ký tự</small>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label fw-bold">Email <span class="text-danger">*</span></label>
                            <input type="email" name="email" class="form-control" 
                                   placeholder="example@email.com" required 
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>
                            <small class="form-text text-muted">Email sẽ được dùng để đăng nhập</small>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label fw-bold">Số điện thoại</label>
                            <input type="tel" name="phone" class="form-control" 
                                   placeholder="0123456789" 
                                   pattern="[0-9]{10,11}" 
                                   title="Số điện thoại phải có 10-11 chữ số"/>
                            <small class="form-text text-muted">Ví dụ: 0123456789</small>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label fw-bold">Địa chỉ</label>
                            <textarea name="address" class="form-control" rows="2" 
                                      placeholder="Nhập địa chỉ của bạn" 
                                      maxlength="200"></textarea>
                            <small class="form-text text-muted">Địa chỉ giao hàng (tùy chọn)</small>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label fw-bold">Mật khẩu <span class="text-danger">*</span></label>
                            <input type="password" name="password" id="password" class="form-control" 
                                   placeholder="Nhập mật khẩu" required 
                                   minlength="6" maxlength="50"/>
                            <small class="form-text text-muted">Tối thiểu 6 ký tự</small>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label fw-bold">Xác nhận mật khẩu <span class="text-danger">*</span></label>
                            <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" 
                                   placeholder="Nhập lại mật khẩu" required/>
                            <small class="form-text text-muted">Nhập lại mật khẩu để xác nhận</small>
                            <div id="passwordMatch" class="mt-1"></div>
                        </div>
                        
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="agreeTerms" required>
                            <label class="form-check-label" for="agreeTerms">
                                Tôi đồng ý với <a href="#" class="text-primary">điều khoản sử dụng</a>
                            </label>
                        </div>
                        
                        <button type="submit" class="btn btn-primary w-100 btn-lg">
                            <i class="fas fa-user-plus"></i> Đăng ký
                        </button>
                        
                        <p class="mt-3 text-center">
                            Đã có tài khoản? 
                            <a href="${pageContext.request.contextPath}/auth?action=login" class="text-primary">
                                Đăng nhập ngay
                            </a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // Kiểm tra mật khẩu khớp
    document.getElementById('confirmPassword').addEventListener('input', function() {
        const password = document.getElementById('password').value;
        const confirmPassword = this.value;
        const matchDiv = document.getElementById('passwordMatch');
        
        if (confirmPassword.length > 0) {
            if (password === confirmPassword) {
                matchDiv.innerHTML = '<small class="text-success"><i class="fas fa-check-circle"></i> Mật khẩu khớp</small>';
            } else {
                matchDiv.innerHTML = '<small class="text-danger"><i class="fas fa-times-circle"></i> Mật khẩu không khớp</small>';
            }
        } else {
            matchDiv.innerHTML = '';
        }
    });
    
    // Validate form trước khi submit
    document.getElementById('registerForm').addEventListener('submit', function(e) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        
        if (password !== confirmPassword) {
            e.preventDefault();
            alert('Mật khẩu xác nhận không khớp!');
            return false;
        }
    });
</script>

<%@ include file="../layout/footer.jsp" %>
