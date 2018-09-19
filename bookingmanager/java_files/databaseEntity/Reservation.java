/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import progmatic.bookingmanager.utils.DateConstants;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern =  DateConstants.DATE_FORMAT)
    private Date startDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern =  DateConstants.DATE_FORMAT)
    private Date endDate;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = DateConstants.TIMEWITHSEC_FORMAT)
    private Date checkInTime;
    @Lob
    @Size(max = 65535)
    private String note;
    private Short breakfast;
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Booking bookingId;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room roomId;
    @NotNull
    private Short paid;

    public Reservation() {
    }

    public Reservation(long id) {
        this.id = id;
    }

    public Reservation(long id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Short getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Short breakfast) {
        this.breakfast = breakfast;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "progmatic.bookingmanager.Reservation[ id=" + id + " ]";
    }

    public Short getPaid() {
        return paid;
    }

    public void setPaid(Short paid) {
        this.paid = paid;
    }
    
    
    
}
