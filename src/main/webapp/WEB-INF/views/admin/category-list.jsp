<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp" />

<div class="container mt-4">
    <h2>Category List</h2>
    <a href="category?action=new" class="btn btn-primary mb-3">Add New Category</a>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${categories}">
            <tr>
                <td>${c.categoryId}</td>
                <td>${c.name}</td>
                <td>
                    <a href="category?action=edit&id=${c.categoryId}" class="btn btn-sm btn-warning">Edit</a>
                    <a href="category?action=delete&id=${c.categoryId}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="../layout/footer.jsp" />
