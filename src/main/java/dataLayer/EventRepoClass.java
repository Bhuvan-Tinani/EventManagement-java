/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataLayer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import javaClass.EventClass;
import QR.GenerateQr;
import javaClass.MemberClass;
import javaClass.OrderDetail;
import javaClass.RegistrationDetail;

/**
 *
 * @author yoges
 */
public class EventRepoClass implements EventRepoInterface{
    Connection con;
    ResultSet rs;
    Statement stmt;
    PreparedStatement pstmt;
    public EventRepoClass(Connection con) {
        this.con=con;
    }
    
    public boolean verifyUser(String username,String password)
    {
        String sql="select count(*) from credential where username=? and password=?";
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            rs.next();
            int rows=rs.getInt(1);
            if(rows==1)
            {
                return true;
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }    
    
    public int addEvent(EventClass event,String username)
    {
        String sql="insert into event(event_name,event_description,"
                    + "event_date,event_photo,event_fees,modifiedBy) values "
                    + "(?,?,?,?,?,?)";
        try{
            java.sql.Date date=java.sql.Date.valueOf(event.getEventDate());
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,event.getEventName());
            pstmt.setString(2,event.getEventDescription());
            pstmt.setDate(3,date);
            pstmt.setBlob(4,event.getImageFile());
            pstmt.setString(5,event.getFees());
            pstmt.setString(6,username);
            int row=pstmt.executeUpdate();
            return row;
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public ArrayList<EventClass> getAllEvent(String path)
    {
        ArrayList<EventClass> listEvent=new ArrayList<EventClass>();
        try{
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from event");
            while(rs.next())
            {
                EventClass event=new EventClass();
                event.setEventId(rs.getInt("id"));
                event.setEventName(rs.getString("event_name"));
                event.setEventDescription(rs.getString("event_description"));
                event.setEventDate(rs.getDate("event_date")+"");
                event.setFees(rs.getString("event_fees"));

                Blob blob = rs.getBlob("event_photo");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[5000];
                int bytesRead = -1;                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                inputStream.close();
                outputStream.close();
                event.setBase64Image(base64Image);
                event.setImageFile(inputStream);
                String url=path+"/RegisterEvent?event_id="+rs.getInt("id");
                String qrBase64=GenerateQr.createQRImage(url,722, "png");
                event.setQrBase64(qrBase64);
                listEvent.add(event);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }
        return listEvent;
    }

    public int updateEvent(EventClass event,String username)
    {
        String sql="update event set event_name=?, event_description=?,"
                    + "event_date=?,event_photo=?, event_fees=?,modifiedBy=? where id=?";
        try{
            java.sql.Date date=java.sql.Date.valueOf(event.getEventDate());
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,event.getEventName());
            pstmt.setString(2,event.getEventDescription());
            pstmt.setDate(3,date);
            pstmt.setBlob(4,event.getImageFile());
            pstmt.setString(5,event.getFees());
            pstmt.setString(6,username);
            pstmt.setInt(7,event.getEventId());
            System.out.println(pstmt);
            int row=pstmt.executeUpdate();
            return row;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public EventClass getEventDetail(int id)
    {
        EventClass event=new EventClass();
        try{
            String sql="select * from event where id=?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                event.setEventId(rs.getInt("id"));
                event.setEventName(rs.getString("event_name"));
                event.setEventDescription(rs.getString("event_description"));
                event.setEventDate(rs.getDate("event_date")+"");
                event.setFees(rs.getString("event_fees"));

                Blob blob = rs.getBlob("event_photo");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[5000];
                int bytesRead = -1;                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                inputStream.close();
                outputStream.close();
                event.setBase64Image(base64Image);
                event.setImageFile(inputStream);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }        
        return event;    
    }

    public boolean checkUserInTable(MemberClass member)
    {
        String sql="select count(*) from user where enrollment=?";
        boolean flag=false;
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,member.getEnrollment());
            rs=pstmt.executeQuery();
            rs.next();
            int rows=rs.getInt(1);
            if(rows==1)
            {
                updateUser(member);
                flag=true;
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public int addUser(MemberClass member)
    {
        String sql="insert into user values (?,?,?,?,?,?,?,?)";
        int row=0;
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,member.getEnrollment());
            pstmt.setString(2,member.getFirstName());
            pstmt.setString(3,member.getLastName());
            pstmt.setString(4,member.getGender());
            pstmt.setString(5,member.getEmail());
            pstmt.setString(6,member.getMobile());
            pstmt.setString(7,member.getDepartment());
            pstmt.setString(8,member.getCourse());
            row=pstmt.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return row;
    }

    public MemberClass getMemberDetails(String enrollment)
    {
        MemberClass member=null;
        try{
            String sql="select * from user where enrollment=?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,enrollment);
            rs=pstmt.executeQuery();
            member=new MemberClass();
            while(rs.next())
            {
                member.setEnrollment(rs.getString("enrollment"));
                member.setFirstName(rs.getString("first_name"));
                member.setLastName(rs.getString("last_name"));
                member.setGender(rs.getString("gender"));
                member.setEmail(rs.getString("email"));
                member.setMobile(rs.getString("mobile"));
                member.setCourse(rs.getString("course"));
                member.setDepartment(rs.getString("department"));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }        
        return member;    
    }

    public int updateUser(MemberClass member)
    {
        String sql="update user set first_name=?, last_name=?,"
                    + "gender=?,email=?,mobile=?,department=?,course=? where enrollment=?";
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,member.getFirstName());
            pstmt.setString(2,member.getLastName());
            pstmt.setString(3,member.getGender());
            pstmt.setString(4,member.getEmail());
            pstmt.setString(5,member.getMobile());
            pstmt.setString(6,member.getDepartment());
            pstmt.setString(7,member.getCourse());
            pstmt.setString(8,member.getEnrollment());
            int row=pstmt.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int addTransactions(OrderDetail orderDetail)
    {
        String sql="insert into transaction(order_id,payment_id,amount,receipt,status,event_id,enrollment,date,time) "
                    + "values (?,?,?,?,?,?,?,?,?)";
        int row=0;
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,orderDetail.getOrder_id());
            pstmt.setString(2,orderDetail.getPayment_id());
            pstmt.setString(3,orderDetail.getAmount());
            pstmt.setString(4,orderDetail.getReceipt());
            pstmt.setString(5,orderDetail.getStatus());
            pstmt.setInt(6,orderDetail.getEvent_id());
            pstmt.setString(7,orderDetail.getEnrollment());
            pstmt.setString(8,orderDetail.getDate());
            pstmt.setString(9,orderDetail.getTime());
            row=pstmt.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return row;
    }

    public int updateTransaction(String order,String payment,String status,String paidTime)
    {
        String sql="update transaction set payment_id=?, status=?,paid_time=? where order_id=? ";
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,payment);
            pstmt.setString(2,status);
            pstmt.setString(3,paidTime);
            pstmt.setString(4,order);
            System.out.println(pstmt);
            int row=pstmt.executeUpdate();
            return row;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public OrderDetail getTransactionId(String orderid)
    {
        OrderDetail order=new OrderDetail();
        try{
            String sql="select * from transaction where order_id=?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,orderid);
            rs=pstmt.executeQuery();
            System.out.println(pstmt);
            while(rs.next())
            {
                order.setTransaction_id(rs.getInt("transaction_id"));
                order.setOrder_id(rs.getString("order_id"));
                order.setPayment_id(rs.getString("payment_id"));
                order.setAmount(rs.getString("amount"));
                order.setReceipt(rs.getString("receipt"));
                order.setStatus(rs.getString("status"));
                order.setEnrollment(rs.getString("enrollment"));
                order.setEvent_id(rs.getInt("event_id"));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }
        return order;
    }

    public int addEventRegistration(OrderDetail orderDetail)
    {
        String sql="insert into event_registration(enrollment,transaction_id,event_id) values (?,?,?)";
        int row=0;
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,orderDetail.getEnrollment());
            pstmt.setInt(2,orderDetail.getTransaction_id());
            pstmt.setInt(3,orderDetail.getEvent_id());
            row=pstmt.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return row;
    }

    public boolean checkUserInEventRegistartion(String enroll,String event)
    {
        String sql="select count(*) from event_registration where enrollment=? and event_id=?";
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,enroll);
            pstmt.setString(2,event);
            rs=pstmt.executeQuery();
            rs.next();
            int rows=rs.getInt(1);
            if(rows>0)
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public ArrayList<MemberClass> getAllUser()
    {
        ArrayList<MemberClass> listmember=new ArrayList<MemberClass>();
        try{
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from user");
            while(rs.next())
            {
                MemberClass member=new MemberClass();
                member.setEnrollment(rs.getString("enrollment"));
                member.setFirstName(rs.getString("first_name"));
                member.setLastName(rs.getString("last_name"));
                member.setGender(rs.getString("gender"));
                member.setEmail(rs.getString("email"));
                member.setMobile(rs.getString("mobile"));
                member.setDepartment(rs.getString("department"));
                member.setCourse(rs.getString("course"));
                listmember.add(member);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }
        return listmember;
    }

    public ArrayList<RegistrationDetail> registrationDetails()
    {
        ArrayList<RegistrationDetail> registrationDetail=new ArrayList<RegistrationDetail>();
        try{
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql="SELECT event_registration.id,event.event_name,event.event_date,event.event_fees,"
            + "event_registration.enrollment,user.first_name,user.last_name,user.department,user.course,"
            + "transaction.transaction_id,transaction.payment_id,transaction.status,transaction.date,transaction.paid_time "
            + "FROM event_registration inner join user "
            + "on event_registration.enrollment=user.enrollment "
            + "inner join event on event_registration.event_id=event.id "
            + "inner join transaction on event_registration.transaction_id=transaction.transaction_id";
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                RegistrationDetail detail=new RegistrationDetail();
                detail.setRegistrationId(rs.getInt("event_registration.id")+"");
                detail.setEventName(rs.getString("event.event_name"));
                detail.setEventDate(rs.getString("event.event_date"));
                detail.setEventFees(rs.getString("event.event_fees"));
                detail.setEnrollment(rs.getString("event_registration.enrollment"));
                detail.setFirstName(rs.getString("user.first_name"));
                detail.setLastName(rs.getString("user.last_name"));
                detail.setDepartment(rs.getString("user.department"));
                detail.setCourse(rs.getString("user.course"));
                detail.setTransactionId(rs.getInt("transaction.transaction_id")+"");
                detail.setPaymentId(rs.getString("transaction.payment_id"));
                detail.setStatus(rs.getString("transaction.status"));
                detail.setDate(rs.getString("transaction.date"));
                detail.setPaidTime(rs.getString("transaction.paid_time"));
                registrationDetail.add(detail);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }
        return registrationDetail;
    }
}