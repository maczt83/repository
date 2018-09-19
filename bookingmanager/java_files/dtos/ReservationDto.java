/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Bence
 */

public class ReservationDto {
    private Date from;
    private Date to;
    private Integer roomNumber;
    private String service;
    private Date arrival;
    private String note;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arriaval) {
        this.arrival = arriaval;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
           
}
