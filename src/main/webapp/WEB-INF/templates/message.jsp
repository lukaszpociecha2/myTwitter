<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 12.06.19
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Messaging system</title>
</head>
<body>
<%--servletRelativeAction="/send-message"--%>
<c:out value="recepeitn id: ${recepient}"/>
<form action="/send" method="post">
    <input type="text" name="text">
    <input type="hidden" name="recepientId" value="<c:out value="${recepient}"/>"/>
    <input type="submit" value="send">
</form>


<%--<f:form method="post" modelAttribute="message">
<f:textarea path="text"></f:textarea>
    <input type="submit" value="send">
    <input hidden type="hidden" value="<c:out value="${recepient}"/>" name="recepient">
</f:form>
//<button type="button" onclick="javascript:window.close();">Cancel</button>--%>
</body>
</html>
