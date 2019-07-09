<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 09.07.19
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:out value="Author: ${message.author.firstName}. Text: ${message.text}"></c:out>
    <a href="/messages/delete?id=${message.id}"><button>Delete</button></a>
</body>
</html>
