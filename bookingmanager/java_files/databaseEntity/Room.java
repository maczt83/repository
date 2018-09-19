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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(1)
    @NotNull
    private short capacity;
    @NotNull
    @Min(0)
    @Max(1)
    private short extraBed;
    @Min(1)
    @NotNull
    private Short building;
    @Min(0)
    private short floor;
    @ManyToMany(mappedBy = "roomList")
    private List<Amenity> amenityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Reservation> reservationList;
    @JoinColumn(name = "roomType_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull
    private RoomType roomType;
    @Min(1)
    @NotNull
    private Integer roomNumber;

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room() {
    }

    public Room(long id) {
        this.id = id;
    }

    
    public Room(long id, short capacity, short extraBed, short floor) {
        this.id = id;
        this.capacity = capacity;
        this.extraBed = extraBed;
        this.floor = floor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public short getExtraBed() {
        return extraBed;
    }

    public void setExtraBed(short extraBed) {
        this.extraBed = extraBed;
    }

    public Short getBuilding() {
        return building;
    }

    public void setBuilding(Short building) {
        this.building = building;
    }

    public short getFloor() {
        return floor;
    }

    public void setFloor(short floor) {
        this.floor = floor;
    }

    public List<Amenity> getAmenityList() {
        return amenityList;
    }

    public void setAmenityList(List<Amenity> amenityList) {
        this.amenityList = amenityList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomTypeid) {
        this.roomType = roomTypeid;
    }

    @Override
    public String toString() {
        return "progmatic.bookingmanager.Room[ id=" + id + " ]";
    }
    
}
