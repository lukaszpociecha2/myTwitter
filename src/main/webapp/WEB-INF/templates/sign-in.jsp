<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 11.06.19
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css"/>

</head>
<body>
<div id="login">

    <form method="post" >
        Enter login/email: <input type="text" name="email"/>
        <p></p>
        Enter password: <input type="password" name="password"/>
        <p></p>
        <input type="submit" value="Log in">
        <c:if test="${error=='true'}">Wrong login!
            <script type="application/javascript" >alert('Wrong email or password')</script>
        </c:if>
    </form>
    <button><a href="/signup">signup</a></button>
</div>
</body>
</html>
