<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 11.06.19
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Main Page</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <%@include file="header.html"%>
</header>
<c:out value="Witaj ${sessionScope.sessionuser.firstName}"/>
<p>
    <button><a href="/signin/logout">Logout</a></button>
</p>

<f:form method="post" modelAttribute="tweet">
    <f:textarea path="text"/><%--maxlength="140"--%><f:errors path="text"/>
    <f:hidden path="user.id" value="${sessionScope.sessionuser.id}"></f:hidden>
    <input type="submit" value="TWEET!!!"/>

</f:form>

<c:if test="${empty alltweets}">
    Empty list
</c:if>
<c:if test="${not empty alltweets}">

    <c:forEach items="${alltweets}" var="wall_tweet">
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

        <form method="post" action="/add-comment">
            <input class="tweet_text" textarea <%--maxlength="60"--%> name="text"/>
            <input type="hidden" name="id" value="${wall_tweet.id}"/>
            <input type="hidden" name="user" value="${sessionScope.sessionuser.id}"/>
            <input type="submit" value="Comment now"/>
        </form>
        <c:if test="${not empty comment}"><script type="application/javascript">alert('${comment}')</script></c:if>

    </c:forEach>




</c:if>
<%--<script type="application/javascript" src="../../resources/script.js"></script>--%>
</body>
</html>
