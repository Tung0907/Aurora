<%@ include file="layout/header.jsp" %>
<div class="container mt-4">
    <h2>Đặt hàng thành công 🎉</h2>
    <p>Đơn hàng của bạn đã được tạo. Mã đơn: <strong>${param.id}</strong></p>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Về trang chủ</a>
</div>
<%@ include file="layout/footer.jsp" %>
