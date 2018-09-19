/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "amenity")
public class Amenity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 1, max = 45)
    private String name;
    @JoinTable(name = "room_has_amenity", joinColumns = {
        @JoinColumn(name = "amenity_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "room_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Room> roomList;

    public Amenity() {
    }

    public Amenity(long id) {
        this.id = id;
    }

    public Amenity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    

    @Override
    public String toString() {
        return "progmatic.bookingmanager.Amenity[ id=" + id + " ]";
    }
    
}
