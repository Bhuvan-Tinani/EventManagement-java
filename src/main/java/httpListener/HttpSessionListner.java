/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package httpListener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author yoges
 */
public class HttpSessionListner implements HttpSessionListener,HttpSessionAttributeListener,HttpSessionBindingListener{
    
    public void sessionCreated(HttpSessionEvent event)
    {
        System.out.println("Session Created");
        Object obj=event.getSource();
        System.out.println(obj.getClass());
    }

    public void sessionDestroyed(HttpSessionEvent event)
    {
        System.out.println("Session destroyed");
    }

    public void attributeAdded(HttpSessionBindingEvent event)
    {
        System.out.println("attribute added");
        System.out.println(event.getName());
        System.out.println(event.getValue());
    }

    public void attributeRemoved(HttpSessionBindingEvent event)
    {
        System.out.println("attribute removed");
    }

    public void attributeReplaced(HttpSessionBindingEvent event)
    {
        System.out.println("attribute replaced");
    }

    public void	valueBound(HttpSessionBindingEvent event)
    {
        System.out.println("value bounded");    
    }

    public void valueUnbound(HttpSessionBindingEvent event)
    {
        System.out.println("value unbounded");
    }
}
