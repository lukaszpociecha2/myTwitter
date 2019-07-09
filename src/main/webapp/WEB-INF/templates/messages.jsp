<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 09.07.19
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Incoming Messages</title>
</head>
<body>
<ul>
    <h4>Incoming messages</h4>
    <c:if test="${empty incoming}">No messages at this time </c:if>
    <c:if test="${not empty incoming}">

    <c:forEach items="${incoming}" var="in">
     <li><c:out value="Message from: ${in.author.firstName}. Message text: ${in.text} Read: ${in.seen}" ></c:out></li>
        <a href="/messages/details?id=${in.id}">Details</a>
        <a href="/messages/delete?id=${in.id}" ><button>Delete message</button></a>
        <button>Respond</button>
    </c:forEach>
    </c:if>

    <h4>Sent messages</h4>
    <c:if test="${empty sent}">No messages at this time </c:if>
    <c:if test="${not empty sent}">

        <c:forEach items="${sent}" var="out">
            <li><c:out value="Message from: ${out.author.firstName}. Message text: ${out.text}" ></c:out></li>
            <button>Respond</button>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>
