/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.eventmanagement;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
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
public class AddEvent extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession(true);
        if(session.isNew())
        {
            RequestDispatcher rd=request.getRequestDispatcher("jspPages/LoginPage.jsp");
            rd.forward(request, response);
        }
        else
        {
            RequestDispatcher rd=request.getRequestDispatcher("jspPages/AddEvent.jsp");
            rd.forward(request, response);
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //BasicAWSCredentials awsCredentials = new BasicAWSCredentials("", "");
        //AmazonS3 s3client = new AmazonS3Client(awsCredentials);
        HttpSession session=request.getSession(true);
        if(session.isNew())
        {
            RequestDispatcher rd=request.getRequestDispatcher("jspPages/LoginPage.jsp");
            rd.forward(request, response);
        }
        String username=(String)session.getAttribute("username");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String event_name=request.getParameter("event_name");
        String event_description=request.getParameter("event_description");
        String event_date=request.getParameter("event_date");
        String event_fees=request.getParameter("event_fees");
        InputStream pic=null;
        String fileName=null;
        String fileUrl=null;
        Part fileInfo=null;
        for(Part part:request.getParts())
        {
            fileName =getFileName(part); 
            if(!(fileName.equals("")))
            {
                System.out.println("cx="+fileName);
                fileInfo=part;
                break;
            }  
            /**/
        }
        pic=fileInfo.getInputStream();

        ServletConfig sc=getServletConfig();
        ServletContext scv=sc.getServletContext();
        Connection con=(Connection)scv.getAttribute("connection");
        try{
            EventRepoClass repo=new EventRepoClass(con);
    
            EventClass event=new EventClass();
            event.setEventName(event_name);
            event.setEventDescription(event_description);
            event.setEventDate(event_date);
            event.setFees(event_fees);
            event.setImageFile(pic);

            int row=repo.addEvent(event,username);

            if(row==0)
            {
                out.println("<script>alert('record Not inserted please try again')</script>");
            }
            request.setAttribute("EventStatus",true);
            RequestDispatcher rd=request.getRequestDispatcher("jspPages/HomePage.jsp");
            rd.forward(request, response);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) 
        {
            System.out.println("token:"+token);
            if (token.trim().startsWith("filename")) {
                System.out.println(token.substring(token.indexOf("=") + 2, token.length()-1));
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
