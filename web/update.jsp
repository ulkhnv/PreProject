<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Users</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        #update {
            position: absolute;
            left: 750px;
            top: -10px  ;
        }
    </style>
</head>
<body>
<h1>Users:</h1>
<table >
    <tr>
        <th width="80">Id</th>
        <th width="120">Name</th>
        <th width="120">Password</th>
        <th width="100">Role</th>
        <th width="100">Age</th>
        <th width="55"></th>
        <th width="50"></th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td align="center">${user.getId()}</td>
            <td align="center">${user.getName()}</td>
            <td align="center">${user.getPassword()}</td>
            <td align="center">${user.getRole()}</td>
            <td align="center">${user.getAge()}</td>
            <td>
                <form action="<c:url value="/admin/update"/>" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Update">
                </form>
            </td>
            <td>
                <form action="<c:url value="/admin/delete"/>" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<div id="update">
    <h1><c:out value="Update user with id : ${id}"/> </h1>
    <form action="<c:url value="/admin/update"/>" method="post">
        <p style="margin-left:11px">Name <input style="margin-left:12px" type="text" name="name"></p>
        <p>Password <input type="text" name="password"></p>
        <p style="margin-left:15px" >Role <input style="margin-left:16px" type="text" name="role"></p>
        <p style="margin-left:17px;" >Age<input style="margin-left:20px" type="number" name="age"></p>
        <input type="hidden" name="id" value="${id}">
        <input type="submit" value="Update" style="margin-left: 178px">
    </form>
</div>
</body>
</html>
