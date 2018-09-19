/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bence
 */
public class GuestBookingViewDto {
    private String guestName;
    private String addressLine1;
    private String addressLine2;
    private String email;
    private String taxNo;
    private String phoneNumber;
    private String zip;
    private String vatid;
    private List<LinkDataDto> linksData=new ArrayList<>();
    

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getVatid() {
        return vatid;
    }

    public void setVatid(String vatid) {
        this.vatid = vatid;
    }

    public List<LinkDataDto> getLinksData() {
        return linksData;
    }

    public void setLinksData(List<LinkDataDto> linksData) {
        this.linksData = linksData;
    }
    public void addLinkData (LinkDataDto linkData){
        linksData.add(linkData);
    }
    
    
    
}
