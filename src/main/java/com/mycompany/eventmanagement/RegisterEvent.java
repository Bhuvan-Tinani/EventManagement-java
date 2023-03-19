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
import javaClass.EventClass;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yoges
 */
public class RegisterEvent extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int event_id=Integer.parseInt(request.getParameter("event_id"));
        ServletConfig sc=getServletConfig();
        ServletContext scv=sc.getServletContext();
        Connection con=(Connection)scv.getAttribute("connection");
        EventRepoClass repo=new EventRepoClass(con);
        EventClass eventDetail=repo.getEventDetail(event_id);
        request.setAttribute("eventDetail",eventDetail);
        RequestDispatcher rd=request.getRequestDispatcher("jspPages/EventRegistration.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
