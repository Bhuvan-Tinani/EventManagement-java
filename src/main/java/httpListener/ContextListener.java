/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package httpListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author yoges
 */
public class ContextListener implements ServletContextListener,ServletContextAttributeListener{
    Connection con;
    public void contextInitialized(ServletContextEvent se)
    {
        ServletContext sc=se.getServletContext();
        String username=sc.getInitParameter("user");
        String password=sc.getInitParameter("password");
        String url=sc.getInitParameter("url");
        String driverclass=sc.getInitParameter("driver");
        try
        {
            Class.forName(driverclass);
            System.out.println(url+" "+username+" "+password);
            con=DriverManager.getConnection(url,username,password);
            sc.setAttribute("connection",con);
            System.out.println("connection created");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("servlet context listner initialized");
    }

    public void contextDestroyed(ServletContextEvent se)
    {
        System.out.println("servlet context listener destroyed");
        ServletContext sc=se.getServletContext();
        sc.removeAttribute("connection");
        try
        {
            con.close();
            System.out.println("connection closed");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void attributeAdded(ServletContextAttributeEvent event)
    {
        System.out.println("attribute added");
    }

    public void attributeRemoved(ServletContextAttributeEvent event)
    {
        System.out.println("attribute removed");
    }

    public void attributeReplaced(ServletContextAttributeEvent event)
    {
        System.out.println("attribute replaced");
    }
}
