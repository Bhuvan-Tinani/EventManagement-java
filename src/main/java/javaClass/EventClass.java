/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaClass;

import java.io.InputStream;

/**
 *
 * @author yoges
 */
public class EventClass {
    int eventId;
    String eventName,eventDescription,eventDate,fees;
    String Base64Image,qrBase64;

    public String getQrBase64() {
        return qrBase64;
    }

    public void setQrBase64(String qrBase64) {
        this.qrBase64 = qrBase64;
    }
    InputStream imageFile;

    public EventClass() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getBase64Image() {
        return Base64Image;
    }

    public void setBase64Image(String Base64Image) {
        this.Base64Image = Base64Image;
    }

    public InputStream getImageFile() {
        return imageFile;
    }

    public void setImageFile(InputStream imageFile) {
        this.imageFile = imageFile;
    }

}
