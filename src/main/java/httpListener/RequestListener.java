/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package httpListener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 *
 * @author yoges
 */
public class RequestListener implements ServletRequestListener,ServletRequestAttributeListener {

    public void requestInitialized(ServletRequestEvent sre)
    {
        System.out.println("request Initialized");
    }

    public void	requestDestroyed(ServletRequestEvent sre)
    {
        System.out.println("request destroyed");
    }

    public void	attributeAdded(ServletRequestAttributeEvent srae)
    {
        System.out.println("attribute added");
        System.out.println(srae.getName());
        System.out.println(srae.getValue());
    }

    public void	attributeRemoved(ServletRequestAttributeEvent srae)
    {
        System.out.println("attribute removed");
        System.out.println(srae.getName());
        System.out.println(srae.getValue());
    }

    public void	attributeReplaced(ServletRequestAttributeEvent srae)
    {
        System.out.println("attribute replced");
    }
}
