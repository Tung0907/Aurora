<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Customer List</title></head>
<body>
<h2>Customer List</h2>
<table border="1">
  <thead><tr><th>ID</th><th>Name</th><th>Email</th></tr></thead>
  <tbody>
  <c:forEach var="c" items="${customers}">
    <tr>
      <td>${c.id}</td>
      <td>${c.name}</td>
      <td>${c.email}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
