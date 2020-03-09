<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<h1><c:out value="${name}"/></h1>
<form method="post" action="/logout">
<input type="submit" value="Logout"/>
</form>
</body>
</html>
