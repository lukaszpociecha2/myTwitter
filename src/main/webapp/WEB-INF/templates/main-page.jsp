<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 11.06.19
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Main Page</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<c:out value="Witaj ${sessionScope.user.firstName}"/>
<p>
    <button><a href="/logout">Logout</a></button>
</p>

<f:form method="post" modelAttribute="tweet">
    <f:textarea path="text" maxlength="140"/>
    <f:hidden path="user.id" value="${sessionScope.user.id}"></f:hidden>
    <input type="submit" value="TWEET!!!"/>

</f:form>


<c:if test="${empty alltweets}">
    Empty list
</c:if>
<c:if test="${not empty alltweets}">

    <c:forEach items="${alltweets}" var="tweet">
        <table id="wall">
            <tr>
                <td><c:out value="${tweet.user.firstName}"></c:out></td>
                <td style="font-style: italic"><c:out value="${tweet.text}"></c:out></td>
                <td style="font-size: 12px"><c:out value="${tweet.created}"></c:out></td>
            </tr>
        </table>
        <c:forEach items="${tweet.commentList}" var="comment">
            <span style="font-size: 11px"><p><c:out value="${comment.user.firstName}"/></span>
            <span style="font-style: italic"><c:out value="${comment.text}"></c:out></span>
            <span style="font-size: 10px"><c:out value="${comment.created}"></c:out></span>
            </p>
        </c:forEach>

        <form method="post" action="/add-comment">
            <input textarea maxlength="60" name="text"/>
            <input type="hidden" name="id" value="${tweet.id}"/>
            <input type="hidden" name="user" value="${sessionScope.user.id}"/>
            <input type="submit" value="Comment now"/>
        </form>

    </c:forEach>




</c:if>

</body>
</html>
