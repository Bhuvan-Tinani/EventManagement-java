/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.eventmanagement;

import dataLayer.EventRepoClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class VierifyLoginCredential extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("EventIndexPage");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("verifyCredential")!=null)
        {
            ServletConfig sc=getServletConfig();
            ServletContext scv=sc.getServletContext();
            Connection con=(Connection)scv.getAttribute("connection");
            String username=request.getParameter("u_name");
            String password=request.getParameter("u_password");
            EventRepoClass repo=new EventRepoClass(con);
            boolean verification=repo.verifyUser(username, password);
            System.out.println(verification);
            if(verification)
            {
                System.out.println("success");
                HttpSession session=request.getSession(true);
                session.setMaxInactiveInterval(300);
                session.setAttribute("username",username);
                RequestDispatcher rd=request.getRequestDispatcher("HomePageSuccess");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("loginfail","true");
                RequestDispatcher rd=request.getRequestDispatcher("jspPages/LoginPage.jsp");
                rd.forward(request, response);
            }
        }
    }

}
