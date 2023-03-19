/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.eventmanagement;

import dataLayer.EventRepoClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import javaClass.EventClass;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yoges
 */
public class ManageEvents extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession session=request.getSession(true);
            if(session.isNew())
            {
                RequestDispatcher rd=request.getRequestDispatcher("jspPages/LoginPage.jsp");
                rd.forward(request, response);
            }
            ServletConfig sc=getServletConfig();
            ServletContext scv=sc.getServletContext();
            String path=scv.getContextPath();
            Connection con=(Connection)scv.getAttribute("connection");
            EventRepoClass repo=new EventRepoClass(con);
            String scheme=request.getScheme();
            String servername=request.getServerName();
            int port=request.getServerPort();
            String servletPath=request.getServletPath();
            String fullPath=scheme+"://"+servername+":"+port+path;
            System.out.println(fullPath);
            ArrayList<EventClass> eventList=repo.getAllEvent(fullPath);
            request.setAttribute("eventList",eventList);
            RequestDispatcher rd=request.getRequestDispatcher("jspPages/ManageEvents.jsp");
            rd.forward(request, response);
        }
        catch(Exception e){
            System.out.println(e.getMessage()); 
            System.out.println(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
