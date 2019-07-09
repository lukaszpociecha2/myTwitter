<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 09.07.19
  Time: 10:14
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
<p><a href="/edit-user">Edit user data</a></p>
<p><a href="/messages">Show messages</a></p>
<p><a href="/delete-account">Delete account</a></p>
<p><a href="/signin/logout">Logout</a></p>


<c:if test="${empty usertweets}">
    Empty list
</c:if>
<c:if test="${not empty usertweets}">

    <c:forEach items="${usertweets}" var="wall_tweet">
        <table id="wall">
        <tr>
        <td><a href="<c:out value="/messages/send/${wall_tweet.user.id}"/>" data-recepient="<c:out value="${tweet.user.id}"/>" class="message_btn"> <c:out value="${wall_tweet.user.firstName}"></c:out></a></td>

        <td style="font-style: italic"><c:out value="${wall_tweet.text}"></c:out></td>
        <td style="font-size: 12px"><c:out value="${wall_tweet.created}"></c:out></td>
        </tr>
        </table>
        <c:forEach items="${wall_tweet.commentList}" var="comment">
            <span style="font-size: 11px"><p><c:out value="${comment.user.firstName}"/></span>
            <span style="font-style: italic"><c:out value="${comment.text}"></c:out></span>
            <span style="font-size: 10px"><c:out value="${comment.created}"></c:out></span>
            </p>
        </c:forEach>
    </c:forEach>
</c:if>
</body>
</html>
