<%-- 
    Document   : HomePage
    Created on : 30 Jan, 2023, 12:46:19 PM
    Author     : yoges
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Hello <c:out value="${sessionScope.username}"/>
        <h1>Hello World!</h1>
        <a href="AddEvent"/>Add Events</a>
        <a href="ManageEvents"/>Manage Events</a>
        <a href="ViewAllUser"/>View User</a>
        <a href="ViewAllRegistration"/>View Registration</a>
        <a href="LogOut"/>Log Out</a>
        <c:if test="${requestScope.EventStatus==true}">
            <script>
                alert("Event Added");
            </script>
        </c:if>
    </body>
</html>
