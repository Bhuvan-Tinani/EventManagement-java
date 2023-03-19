<%-- 
    Document   : LoginPage
    Created on : 28 Jan, 2023, 12:28:17 PM
    Author     : yoges
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <c:if test="${requestScope.loginfail == true}">
            <script>
                window.alert("incorrect username or password");
            </script>
        </c:if>
        <form action="VierifyLoginCredential" method="post">
            <img src="images/img_avatar1.png" alt="alt" height="100" width="100"/><br>
            Username: <input type="text" name="u_name" required/><br>
            Password: <input type="password" name="u_password" required/><br>
            <input type="submit" value="submit" name="verifyCredential"/>
        </form>
    </body>
</html>