/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import progmatic.bookingmanager.utils.DateConstants;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "roomRate")
public class RoomRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = DateConstants.DATE_FORMAT)
    private Date startDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = DateConstants.DATE_FORMAT)
    private Date endDate;
    @NotNull
    private int rate;
    
    @JoinColumn(name = "roomType_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RoomType roomTypeid;

    public RoomRate() {
    }

    public RoomRate(long id) {
        this.id = id;
    }

    public RoomRate(long id, Date startDate, Date endDate, int rate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "progmatic.bookingmanager.RoomRate[ id=" + id + " ]";
    }

    public RoomType getRoomTypeid() {
        return roomTypeid;
    }

    public void setRoomTypeid(RoomType roomTypeid) {
        this.roomTypeid = roomTypeid;
    }
    
    
    
}
