<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 11.06.19
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>All Tweets</title>
</head>
<body>
Tutaj powinny byc tweety
    <c:if test="${empty alltweets}">
        Empty list
    </c:if>
    <c:if test="${not empty alltweets}">
        Not empty <c:out value="${alltweets.size()}"></c:out>

        <c:forEach items="${alltweets}" var="tweet">
            <c:out value="${tweet.user.firstName}"></c:out>
            <c:out value="${tweet.text}"></c:out>
        </c:forEach>

    </c:if>

</body>
</html>
