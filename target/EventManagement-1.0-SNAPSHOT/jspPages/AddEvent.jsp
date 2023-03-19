<%-- 
    Document   : AddEvent
    Created on : 30 Jan, 2023, 1:38:27 PM
    Author     : yoges
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AddEvent" method="post" enctype="multipart/form-data">
            <a href="AddEvent"/>Add Events</a>
            <a href="ManageEvents"/>Manage Events</a>
            <a href="ViewAllUser"/>View User</a>
            <a href="ViewAllRegistration"/>View Registration</a>
            <a href="LogOut"/>Log Out</a>
            <h1>Add Event</h1><br>
            <fieldset>
            <legend>Event Details</legend>
            <table>
                <tr>
                    <td><label>Event Name</label></td>
                    <td colspan="2"><input type="text" name="event_name"  required/></td>
                </tr>
                <tr>
                    <td><label>Event Description</label></td>
                    <td><textarea name="event_description" rows="3" cols="30" required></textarea></td>
                </tr>
                <tr>
                    <td><label>Event Date</label></td>
                    <td><input type="date" name="event_date" required/></td>
                </tr>
                <tr>
                    <td><label>upload Photo</label></td>
                    <td><input type="file" name="eventPhoto" accept="image/*" required/></td>
                </tr>
                <tr>
                    <td><label>Event Fees</label></td>
                    <td><input type="number" name="event_fees" required/></td>
                </tr>
                <tr>
                    <td colspan="2"><center><input type="submit" value="Add Event"/></center></td>
                </tr>
                
            </table>       
            </fieldset>
        </form>
    </body>
</html>
