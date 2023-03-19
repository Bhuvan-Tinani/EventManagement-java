/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.eventmanagement;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dataLayer.EventRepoClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClass.OrderDetail;
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
public class CreatePayment extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out=response.getWriter();
        String enrollment=request.getParameter("enroll");
        int event_id=Integer.parseInt(request.getParameter("event_id"));
        int amt=Integer.parseInt(request.getParameter("amt"));
        String date=request.getParameter("date");
        String time=request.getParameter("curtime");
        RazorpayClient payment;
        ServletConfig sc=getServletConfig();
        ServletContext scv=sc.getServletContext();
        Connection con=(Connection)scv.getAttribute("connection");
        try {
            payment = new RazorpayClient("rzp_test_qp4uEp2A5RYhgI","R9IENBdt2w2RIS63Ibhsq4aK");
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amt*100); // amount in the smallest currency unit
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");
            Order order=payment.orders.create(orderRequest);
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setAmount(order.get("amount")+"");
            System.out.println(order.get("id"));
            orderDetail.setOrder_id(order.get("id")+"");
            orderDetail.setPayment_id(null);
            orderDetail.setStatus("created");
            orderDetail.setReceipt(order.get("receipt")+"");
            orderDetail.setEvent_id(event_id);
            orderDetail.setEnrollment(enrollment);
            orderDetail.setDate(date);
            orderDetail.setTime(time);
            System.out.println("order created");
            EventRepoClass repo=new EventRepoClass(con);
            int row=repo.addTransactions(orderDetail);
            out.println(order);
        } catch (RazorpayException ex) {
            Logger.getLogger(CreatePayment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
