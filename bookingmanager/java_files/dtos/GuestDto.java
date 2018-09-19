/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Anna
 */
public class GuestDto {
    
    private long id;
  
    @NotNull
    @Size(min = 1, max = 60)
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String emailAddress;
    
    @NotNull
    @Size(min = 1, max = 13)
    @Pattern(regexp = "^\\d{8,13}$")
    private String phoneNumber;
    
    @NotNull
    @Size(min = 1, max = 7)
    private String title;
    
    @Size(max = 45)
    private String city;
    
    @Size(max = 100)
    private String address1;
    
    @Size(max = 10)
    private String zip;
    
    @NotNull
    private String country;
    
    @Size(max = 13)
    private String taxNo;
   
    @Size(max = 15)
    private String vatID;
    
    //@Size(min = 1, max = 60)
    //@Pattern(regexp = "^([A-ZÁÉÍÓÖŐÜŰÚ]([a-záéíöüóőúű]+([- ])?|'|. ))+.?$")
    private String firstName;
    
    //@Size(min = 1, max = 60)
    //@Pattern(regexp = "^([A-ZÁÉÍÓÖŐÜŰÚ]([a-záéíöüóőúű]+([- ])?|'|. ))+.?$")
    private String lastName;
    
    //@Size(min =2, max = 70)
    private String companyName;

    public GuestDto(long id, String emailAddress, String phoneNumber, 
            String city, String address1, String zip, String country, 
            String title, String taxNo, String vatID, 
            String firstName, String lastName) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.address1 = address1;
        this.zip = zip;
        this.country = country;
        this.title = title;
        this.taxNo = taxNo;
        this.vatID = vatID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public GuestDto(long id, String emailAddress, String phoneNumber, 
            String city, String address1, String zip, String country, 
            String title, String taxNo, String vatID, 
            String companyName) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.address1 = address1;
        this.zip = zip;
        this.country = country;
        this.title = title;
        this.taxNo = taxNo;
        this.vatID = vatID;
        this.companyName = companyName;
    }
    
    public GuestDto(){
        
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getVatID() {
        return vatID;
    }

    public void setVatID(String vatID) {
        this.vatID = vatID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
