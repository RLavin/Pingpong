<%--
  Created by IntelliJ IDEA.
  User: Raul
  Date: 11/14/16
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Login Here</title>
</head>
<body>
<div>
    <c:out value="${message}"/>
</div>
<form action="/login" method="post">
    <div>
        User: <input type="text" name="username"/>
    </div>
    <div>
        Password: <input type="password" name="password"/>
    </div>
    <input type="submit" name="Login">
</form>
</body>
</html>