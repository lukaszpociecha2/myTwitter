<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 11.06.19
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div>
        <f:form modelAttribute="user" method="post">
            Enter first name: <f:input path="firstName"/><f:errors path="firstName"/>
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
            <input type="submit" value="Sign up" id="submit_btn">
        </f:form>
    </div>

<script type="application/javascript" src="../../resources/create_user.js"></script>
</body>
</html>
