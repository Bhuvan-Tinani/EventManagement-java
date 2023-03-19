<%-- 
    Document   : ViewRegistrations
    Created on : 14 Feb, 2023, 5:43:53 PM
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
                    <th>Registration id</th>
                    <th>Event Name</th>
                    <th>Event Date</th>
                    <th>Event Fees</th>
                    <th>Enrollment</th>
                    <th>First Name</th>
                    <th>Last name</th>
                    <th>Department</th>
                    <th>Course</th>
                    <th>Transaction Id</th>
                    <th>Payment Id</th>
                    <th>Status</th>
                    <th>Payment Date</th>
                    <th>Payment Time</th>
                </tr>
                <c:if test="${requestScope.listregisteration.size() == 0}">
                    <td colspan="12"><center><h2>No Data</h2></center></td>
                </c:if>
                <c:if test="${requestScope.listregisteration.size() > 0}">
                    <c:forEach items="${requestScope.listregisteration}" var="detail">
                        <tr>
                            <td><c:out value="${detail.getRegistrationId()}"/></td> 
                            <td><c:out value="${detail.getEventName()}"/></td> 
                            <td><c:out value="${detail.getEventDate()}"/></td> 
                            <td><c:out value="${detail.getEventFees()}"/></td> 
                            <td><c:out value="${detail.getEnrollment()}"/></td> 
                            <td><c:out value="${detail.getFirstName()}"/></td> 
                            <td><c:out value="${detail.getLastName()}"/></td> 
                            <td><c:out value="${detail.getDepartment()}"/></td> 
                            <td><c:out value="${detail.getCourse()}"/></td> 
                            <td><c:out value="${detail.getTransactionId()}"/></td> 
                            <td><c:out value="${detail.getPaymentId()}"/></td> 
                            <td><c:out value="${detail.getStatus()}"/></td> 
                            <td><c:out value="${detail.getDate()}"/></td> 
                            <td><c:out value="${detail.getPaidTime()}"/></td>                                  
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </body>
</html>
