/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anna
 */
public class ArrivingLeavingGuests {
    
    private List<GuestReportDto> arrivingGuests = new ArrayList<>();
    private List<GuestReportDto> leavingGuests = new ArrayList<>();

    public List<GuestReportDto> getArrivingGuests() {
        return arrivingGuests;
    }

    public void setArrivingGuests(List<GuestReportDto> arrivingGuests) {
        this.arrivingGuests = arrivingGuests;
    }

    public List<GuestReportDto> getLeavingGuests() {
        return leavingGuests;
    }

    public void setLeavingGuests(List<GuestReportDto> leavingGuests) {
        this.leavingGuests = leavingGuests;
    }
    
    
    
    
    
}
