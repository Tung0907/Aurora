<%@ include file="layout/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-md-4">
        <h2 class="mb-3">📝 Đăng ký</h2>
        <form method="post" action="auth">
            <input type="hidden" name="action" value="register"/>
            <div class="mb-3">
                <label>Họ tên</label>
                <input type="text" name="name" class="form-control" required/>
            </div>
            <div class="mb-3">
                <label>Email</label>
                <input type="email" name="email" class="form-control" required/>
            </div>
            <div class="mb-3">
                <label>Mật khẩu</label>
                <input type="password" name="password" class="form-control" required/>
            </div>
            <button class="btn btn-success w-100">Đăng ký</button>
        </form>
    </div>
</div>
<%@ include file="layout/footer.jsp" %>
