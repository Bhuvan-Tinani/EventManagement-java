/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.eventmanagement;

import dataLayer.EventRepoClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javaClass.EventClass;
import javaClass.OrderDetail;
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
public class UpdatePayment extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out=response.getWriter();
        String payment=request.getParameter("payment");
        String order=request.getParameter("order");
        String status=request.getParameter("status");
        String paidTime=request.getParameter("paidtime");
        ServletConfig sc=getServletConfig();
        ServletContext scv=sc.getServletContext();
        Connection con=(Connection)scv.getAttribute("connection");
        try{
            System.out.println(payment+" "+order+" "+status+" "+paidTime);
            EventRepoClass repo=new EventRepoClass(con);
            int row=repo.updateTransaction(order, payment, status,paidTime);
            OrderDetail orderdetail=repo.getTransactionId(order);
            System.out.println(orderdetail.getTransaction_id());
            int row1=repo.addEventRegistration(orderdetail);
            System.out.println("payment updated");
            System.out.println(row);
            if(row>0)
            {
                out.println("Payment successfull");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            out.println(e.getMessage());
            System.out.println(e);
        }
    }
}
