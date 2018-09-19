/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.io.Serializable;
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

/**
 *
 * @author chris
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Guest guestId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
    private List<Reservation> reservationList;

    public Booking() {
    }

    public Booking(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Guest getGuestId() {
        return guestId;
    }

    public void setGuestId(Guest guestId) {
        this.guestId = guestId;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public String toString() {
        return "progmatic.bookingmanager.Booking[ id=" + id + " ]";
    }
    public void addReservation(Reservation reservation){
        reservationList.add(reservation);
    }
    
}
