<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All Users</title>
</head>
<body>

<table id="userTable">
    <thead>
    <tr>
        <th></th>
        <th>ID</th>
        <th>Name</th>
        <th>Role</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <td>${user.idUser}</td>
            <td>${user.username}</td>
            <td>${user.userRightsEnum.name()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
