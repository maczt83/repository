/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Anna
 */
public class GuestReportDto {
    
//    private String lastName;
//    private String firstName;
//    private String companyName;
    private String name;
    private Integer roomNumber;
    private Date checkInTime;
    private Short paid;

    public Short getPaid() {
        return paid;
    }

    public void setPaid(Short paid) {
        this.paid = paid;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

   

    

    
    
    
}
