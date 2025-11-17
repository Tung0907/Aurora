<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="d-flex justify-content-between mb-3">
    <h2 class="text-gradient">🏷️ Quản lý danh mục</h2>
    <a href="admin/categories?action=create" class="btn btn-primary"><i class="fa fa-plus"></i> Thêm danh mục</a>
</div>
<table class="table table-bordered shadow">
    <thead class="text-white" style="background:linear-gradient(90deg,#6a11cb,#2575fc);">
    <tr><th>ID</th><th>Tên</th><th>Hành động</th></tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${categories}">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>
                <a href="admin/categories?action=edit&id=${c.id}" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i></a>
                <a href="admin/categories?action=delete&id=${c.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="../layout/footer.jsp" %>
