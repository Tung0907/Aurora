<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đặt hàng thành công</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light d-flex flex-column align-items-center justify-content-center vh-100">

<div class="card shadow p-4 text-center" style="max-width: 500px;">
    <h2 class="text-success mb-3">✅ Đặt hàng thành công!</h2>
    <p>Cảm ơn bạn đã mua sắm tại <strong>Aurora Jewelry</strong>.</p>

    <c:if test="${not empty orderId}">
        <p>Mã đơn hàng của bạn: <span class="fw-bold">#${orderId}</span></p>
    </c:if>

    <div class="mt-4 d-flex justify-content-center gap-3">
        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">
            ⬅ Tiếp tục mua sắm
        </a>
        <!-- Nếu bạn không muốn nút thanh toán nữa thì bỏ đi -->
    </div>
</div>

</body>
</html>
