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
import javax.json.JsonObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author My PC
 */
public class GetUserDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String enrollment=request.getParameter("enroll");
        ServletConfig sc=getServletConfig();
        ServletContext scv=sc.getServletContext();
        Connection con=(Connection)scv.getAttribute("connection");
        EventRepoClass repo=new EventRepoClass(con);
        MemberClass member=repo.getMemberDetails(enrollment);
        if(member.getEnrollment()!=null)
        {
            JSONObject json=new JSONObject();
            json.put("enrollment",member.getEnrollment());
            json.put("fname",member.getFirstName());
            json.put("lname",member.getLastName());
            json.put("gender",member.getGender());
            json.put("email",member.getEmail());
            json.put("mobile",member.getMobile());
            out.println(json);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
