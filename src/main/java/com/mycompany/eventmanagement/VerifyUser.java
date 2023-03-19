/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.eventmanagement;

import dataLayer.EventRepoClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javaClass.MemberClass;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author My PC
 */
public class VerifyUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enrollment=request.getParameter("enroll");
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String gender=request.getParameter("gender");
        String email=request.getParameter("email");
        String mob=request.getParameter("mob");
        String dept=request.getParameter("dept");
        String course=request.getParameter("course");
        PrintWriter out=response.getWriter();
        MemberClass member=new MemberClass();
        member.setEnrollment(enrollment);
        member.setFirstName(fname);
        member.setLastName(lname);
        member.setGender(gender);
        member.setEmail(email);
        member.setMobile(mob);
        member.setCourse(course);
        member.setDepartment(dept);
        ServletConfig sc=getServletConfig();
        ServletContext scv=sc.getServletContext();
        Connection con=(Connection)scv.getAttribute("connection");
        EventRepoClass repo=new EventRepoClass(con);
        boolean checkUser=repo.checkUserInTable(member);
        if(!(checkUser))
        {
            int row=repo.addUser(member);
        }
        out.println(enrollment+" "+fname+" "+lname+" "+gender+" "+email+" "+mob+" "+course+" "+dept);
    }

    
}
