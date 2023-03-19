/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataLayer;

import java.util.ArrayList;
import javaClass.EventClass;
import javaClass.MemberClass;

/**
 *
 * @author yoges
 */
public interface EventRepoInterface {
    public boolean verifyUser(String username,String password);
    public int addEvent(EventClass event,String username);
    public ArrayList<EventClass> getAllEvent(String path);
    public int updateEvent(EventClass event,String username);
    public EventClass getEventDetail(int id);
    public boolean checkUserInTable(MemberClass member);
    public int addUser(MemberClass member);
    public MemberClass getMemberDetails(String enrollment);
    public int updateUser(MemberClass member);
}
