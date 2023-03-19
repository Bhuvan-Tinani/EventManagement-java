<%-- 
    Document   : ViewUsers
    Created on : 14 Feb, 2023, 5:25:40 PM
    Author     : My PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="f1" method="post" enctype="multipart/form-data">
            <a href="AddEvent"/>Add Events</a>
            <a href="ManageEvents"/>Manage Events</a>
            <a href="ViewAllUser"/>View User</a>
            <a href="ViewAllRegistration"/>View Registration</a>
            <a href="LogOut"/>Log Out</a>
            <table border="1" id="tableContent">
                <caption>Events Details</caption>
                <tr>
                    <th>Enrollment</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>department</th>
                    <th>Course</th>
                </tr>
                <c:if test="${requestScope.listmember.size() == 0}">
                    <td colspan="8"><center><h2>No Data</h2></center></td>
                </c:if>
                <c:if test="${requestScope.listmember.size() > 0}">
                    <c:forEach items="${requestScope.listmember}" var="memberinfo">
                        <tr>
                            <td><c:out value="${memberinfo.getEnrollment()}"/></td> 
                            <td><c:out value="${memberinfo.getFirstName()}"/></td> 
                            <td><c:out value="${memberinfo.getLastName()}"/></td> 
                            <td><c:out value="${memberinfo.getGender()}"/></td> 
                            <td><c:out value="${memberinfo.getEmail()}"/></td> 
                            <td><c:out value="${memberinfo.getMobile()}"/></td> 
                            <td><c:out value="${memberinfo.getDepartment()}"/></td> 
                            <td><c:out value="${memberinfo.getCourse()}"/></td>                             
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </body>
</html>
