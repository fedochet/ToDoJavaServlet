<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 14.06.2016
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ToDo List App</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<form method="post" action="">
    New ToDo: <br>
    <input type="text" name="new_task"><br>
    <input type="submit" value="Add">
</form>
<c:out value="${new_task}"/>
</body>
</html>
