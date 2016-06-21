<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 14.06.2016
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ToDo List App</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<form method="post" action="add">
    New ToDo: <br>
    <input type="text" name="new_task"><br>
    <input type="submit" value="Add">
</form>
<B>Uncompleted items</B><br/>
<c:forEach items="${uncompleted_items}" var="item">
    <form action="complete" method="post">
        <input type="checkbox" onclick="this.form.submit();" name="task_name" value="<c:out value="${item}"/>"><c:out value="${item}"/><br/>
    </form>
</c:forEach>
<B>Completed items</B><br/>
<c:forEach items="${completed_items}" var="item">
    <form action="uncomplete" method="post">
        <input type='hidden' name="task_name" value="<c:out value="${item}"/>">
        <input type="checkbox" onclick="this.form.submit();" checked="true"><c:out value="${item}"/><br/>
    </form>
</c:forEach>
<%--<input name="your_name" value="your_value" type="checkbox">Checkbox text<br/>--%>
</body>
</html>
