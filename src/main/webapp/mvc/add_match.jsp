<%--
  Created by IntelliJ IDEA.
  User: Raul
  Date: 11/15/16
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Match</title>
</head>
<body>
Please input your Match data and click 'Save'!

<form action="/mvc/match/add"method="post">
    <table>
        <tr> <td>Player One:</td>              <td><input type="text" name="playerOne"></td></tr>
        <tr><td>Player One Score:</td>          <td><input type="text" name="playerOneScore"></td></tr>
        <tr> <td>Player Two:</td>    <td><input type="text" name="playerTwo"></td></tr>
        <tr> <td>Player Two Score:</td>    <td><input type="text" name="playerTwoScore"></td></tr>

    </table>
    <input type="submit" name="save" />

</form>
</body>
</html>

