/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.eventmanagement;

import dataLayer.EventRepoClass;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import javaClass.EventClass;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author yoges
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,maxFileSize=1024*1024*50,maxRequestSize=1024*1024*100)
public class EditEvent extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession(true);
        if(session.isNew())
        {
            RequestDispatcher rd=request.getRequestDispatcher("jspPages/LoginPage.jsp");
            rd.forward(request, response);
        }
        String username=(String)session.getAttribute("username");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        ServletConfig sc=getServletConfig();
        ServletContext scv=sc.getServletContext();
        Connection con=(Connection)scv.getAttribute("connection");
        int id=Integer.parseInt(request.getParameter("event_id"));
        String eventName=request.getParameter("event_name");
        String eventDescription=request.getParameter("event_description");
        String eventDate=request.getParameter("event_date");
        String eventFees=request.getParameter("event_fees");        

        InputStream pic=null;
        String fileName=null;
        String fileUrl=null;
        Part fileInfo=null;
        for(Part part:request.getParts())
        {
            fileName =getFileName(part); 
            if(!(fileName.equals("")))
            {
                //System.out.println("cx="+fileName);
                fileInfo=part;
                break;
            }  
            /**/
        }
        pic=fileInfo.getInputStream();
        try{
            EventRepoClass repo=new EventRepoClass(con);

            EventClass event=new EventClass();
            event.setEventId(id);
            event.setEventName(eventName);
            event.setEventDescription(eventDescription);
            event.setEventDate(eventDate);
            event.setFees(eventFees);
            event.setImageFile(pic);

            int row=repo.updateEvent(event,username);

            if(row==0)
            {                
                //out.println("<script>alert('record Not updated please try again')</script>");
                out.println("Record Edited");
            }
            /*request.setAttribute("updatedId", id);
            RequestDispatcher rd=request.getRequestDispatcher("StudentIndexPage");
            rd.forward(request, response);*/
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            out.println(e.getMessage());
            System.out.println(e);
        }     
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        //System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) 
        {
            System.out.println("token:"+token);
            if (token.trim().startsWith("filename")) {
                //System.out.println(token.substring(token.indexOf("=") + 2, token.length()-1));
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
