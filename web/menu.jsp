<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        #add {
            position: absolute;
            left: 550px;
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
        <th width="100">Age</th>
        <th width="55"></th>
        <th width="50"></th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td align="center">${user.getId()}</td>
            <td align="center">${user.getName()}</td>
            <td align="center">${user.getAge()}</td>
            <td>
                <form action="<c:url value="/update"/>" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Update">
                </form>
            </td>
            <td>
                <form action="<c:url value="/delete"/>" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<div id="add">
    <h1>Add User</h1>
    <form action="<c:url value="/menu"/>" method="post">
        <p>Name <input type="text" name="name"></p>
        <p style="margin-left:7px;" id="age">Age<input style="margin-left:8px" type="number" name="age"></p>
        <input type="submit" value="Add" style="margin-left: 175px">
    </form>
</div>
</body>
</html>

