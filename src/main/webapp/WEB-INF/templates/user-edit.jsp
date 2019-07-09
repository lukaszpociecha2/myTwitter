<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 12.06.19
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<div>
    <f:form modelAttribute="user" method="post">
        Enter first name: <f:input path="firstName"/>
        <f:errors path="firstName"/>
        <p></p>
        Enter last name: <f:input path="lastName"/><f:errors path="lastName"/>
        <p></p>
        Enter email: <f:input path="email"></f:input><f:errors path="email"/>
        <p>
            <c:if test="${notunique==true}">email already exists</c:if>
        </p>
        Enter password: <f:password id="password" path="password"></f:password><f:errors path="password"/>
        <p></p>
        Repeat password <input id="repeat_password" type="password">
        <f:hidden path="id" value="${sessionScope.sessionuser.id}"/>
        <input type="submit" value="Submit changes" id="submit_btn">
    </f:form>
</div>
<script type="application/javascript" src="../../resources/create_user.js"></script>
</body>
</html>
