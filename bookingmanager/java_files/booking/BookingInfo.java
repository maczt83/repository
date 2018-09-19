package progmatic.bookingmanager.booking;

public class BookingInfo {

    private long id;
    private String name;
    private String country;
    private long numOfRes;
    private String emailAddress;

    public BookingInfo() {
    }

    public long getId() {
        return id;
    }
            
    public String getName() {
        return name;
    }
    
    public String getCountry() {
        return country;
    }

    public long getNumOfRes() {
        return numOfRes;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
        
    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumOfRes(long numOfRes) {
        this.numOfRes = numOfRes;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
