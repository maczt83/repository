package progmatic.bookingmanager.reservation;

import java.util.Date;

public class ReservationInfo {

    private Date startDate;
    private Date endDate;
    private String name;
    private String emailAddress;
    private String roomTypeName;
    private long id;
    private Integer roomNumber;
    private String service;
    private Date arrival;
    private String note;
    private String phoneNumber;
    

    public ReservationInfo() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }
        
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public long getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getService() {
        return service;
    }

    public Date getArrival() {
        return arrival;
    }

    public String getNote() {
        return note;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
