<%--
  Created by IntelliJ IDEA.
  User: Raul
  Date: 11/15/16
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update New Match</title>
</head>
<body>
Please Edit your Match data and click 'Update'!

<form action="/mvc/match/add" method="post">
    <table>
        <tr> <td>Player One:</td>              <td><input type="text" name="playerOne" value="<c:out value="${myEdit.playerOne}"/>"></td></tr>
        <tr><td>Player One Score:</td>          <td><input type="text" name="playerOneScore" value="<c:out value="${myEdit.playerOneScore}"/>"></td></tr>
        <tr> <td>Player Two:</td>    <td><input type="text" name="playerTwo" value="<c:out value="${myEdit.playerTwo}"/>"></td></tr>
        <tr> <td>Player Two Score:</td>    <td><input type="text" name="playerTwoScore" value="<c:out value="${myEdit.playerTwoScore}"/>"></td></tr>

    </table>
    <input type="submit" name="Update" />
    <input type="hidden" name="id" value="<c:out value="${myEdit.id}"/>"/>

</form>
</body>
</html>
