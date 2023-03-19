<%-- 
    Document   : ManageEvents
    Created on : 30 Jan, 2023, 4:03:39 PM
    Author     : yoges
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function confirmationEdit()
            {
                confirmation=confirm("are you sure you want to Edit the record ?");
                return confirmation;
            }
            function eventName(cell_)
            {
                val=cell_.innerText;
                inputTexts=document.createElement("input");
                inputTexts.setAttribute("type","text");
                inputTexts.setAttribute("value",val);
                inputTexts.setAttribute("name","eventName");
                cell_.innerText=null;
                cell_.appendChild(inputTexts);
            }
            function eventDescription(cell_)
            {
                val=cell_.innerText;
                inputTexts=document.createElement("textarea");
                inputTexts.setAttribute("name","eventDescription");
                textVal= document.createTextNode(val);
                inputTexts.append(textVal);
                cell_.innerText=null;
                cell_.appendChild(inputTexts);
            }
            function eventDate(cell_)
            {
                val=cell_.innerText;
                inputTexts=document.createElement("input");
                inputTexts.setAttribute("type","date");
                inputTexts.setAttribute("value",val);
                inputTexts.setAttribute("name","eventDate");
                cell_.innerText=null;
                cell_.appendChild(inputTexts);
            }
            function eventFees(cell_)
            {
                val=cell_.innerText;
                inputTexts=document.createElement("input");
                inputTexts.setAttribute("type","number");
                inputTexts.setAttribute("value",val);
                inputTexts.setAttribute("name","eventFees");
                cell_.innerText=null;
                cell_.appendChild(inputTexts);
            }
            function uploadImg(cell_)
            {
                val=cell_.childNodes;
                console.log(val[0].alt);
                path="c:/"+(val[0].alt);
                inputTexts=document.createElement("input");
                inputTexts.setAttribute("type","file");
                inputTexts.setAttribute("accept","image/*");                
                inputTexts.setAttribute("id","img1");  
                cell_.appendChild(inputTexts);
            }
            function initializeButton(cell_,id_event)
            {
                cell_.innerText=null;
                saveButton=document.createElement("input");
                saveButton.setAttribute("type","button");
                saveButton.setAttribute("value","save");
                saveButton.setAttribute("onclick","saveData("+id_event+")");
                cell_.appendChild(saveButton);
                cancelButton=document.createElement("input");
                cancelButton.setAttribute("type","button");
                cancelButton.setAttribute("value","cancel");
                cancelButton.setAttribute("onclick","(location.reload())");
                cell_.appendChild(cancelButton);
            }
            function viewRow(val)
            {
                data=document.getElementById(val);
                table1=document.getElementById("tableContent");
                for(i=1;i<table1.rows.length;i++)
                {
                    if(table1.rows[i]!=data)
                    {
                        info=table1.rows[i].cells;
                        info[6]=null;
                        info[6].innerText="EDIT"
                    }
                }
                row_=data.cells;
                id_event=row_[0].innerText;
                eventName(row_[1]);
                eventDescription(row_[2]);                
                eventDate(row_[3]);
                eventFees(row_[4]);
                uploadImg(row_[5]);
                initializeButton(row_[6],id_event);
                return false;
            }
            function saveData(event_id)
            {
                confirmation_=confirmationEdit();
                if(confirmation_)
                {
                    eventName=document.f1.eventName.value;
                    eventDescription=document.f1.eventDescription.value;
                    eventDate=document.f1.eventDate.value;
                    eventFees=document.f1.eventFees.value;
                    sendFile(event_id,eventName,eventDescription,eventDate,eventFees);
                }
            }
            function sendFile(event_id,eventName,eventDescription,eventDate,eventFees)
            {
                fileSelect = document.getElementById('img1');
                FILE_=fileSelect.files;
                file = FILE_[0];
                console.log(event_id,eventName,eventDescription,eventDate,eventFees);
                formData = new FormData();
                formData.append("event_id",event_id);
                formData.append("event_name",eventName);
                formData.append("event_description",eventDescription);
                formData.append("event_date",eventDate);
                formData.append("event_fees",eventFees);
                formData.append('img1', file);    
                console.log(formData.get("eventName"));
                request=new XMLHttpRequest();
                request.onreadystatechange=function()
                {
                    if (this.readyState == 4 && this.status == 200) {
                        location.reload();
                        alert(this.responseText);
                    }
                }                                
                request.open("POST","<%=application.getContextPath()%>/EditEvent",true);
                request.send(formData);
            }
        </script>
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
                    <th>id</th>
                    <th>Event Name</th>
                    <th>Event Description</th>
                    <th>Event Date</th>
                    <th>Event Fees</th>
                    <th>Event Image</th>
                    <th>Edit</th>
                    <th>Download QR</th>
                </tr>
                <c:if test="${requestScope.eventList.size() == 0}">
                    <td colspan="8"><center><h2>No Data</h2></center></td>
                </c:if>
                <c:if test="${requestScope.eventList.size() > 0}">
                    <c:forEach items="${requestScope.eventList}" var="eventInfo">
                        <tr id="tr_${eventInfo.getEventId()}">
                            <td><c:out value="${eventInfo.getEventId()}"/></td> 
                            <td><c:out value="${eventInfo.getEventName()}"/></td> 
                            <td><c:out value="${eventInfo.getEventDescription()}"/></td> 
                            <td><c:out value="${eventInfo.getEventDate()}"/></td> 
                            <td><c:out value="${eventInfo.getFees()}"/></td> 
                            <td><img src="data:image/*;base64,<c:out value='${eventInfo.getBase64Image()}'/>" alt="alt" height="100" width="100"/></td> 
                            <td><a href="" onclick="return viewRow('tr_${eventInfo.getEventId()}')">EDIT</a></td>
                            <td><a download="${eventInfo.getEventName()}_QR.png" href="data:image/png;base64,<c:out value='${eventInfo.getQrBase64()}'/>"><img src="data:image/*;base64,<c:out value='${eventInfo.getQrBase64()}'/>" alt="alt" height="100" width="100"/></a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </body>
</html>