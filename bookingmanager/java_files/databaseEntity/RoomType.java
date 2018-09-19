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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "roomType")
public class RoomType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 1, max = 45)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomType")
    private List<Room> roomList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomTypeid")
    private List<RoomRate> roomRateList;

    public RoomType() {
    }

    public RoomType(long id) {
        this.id = id;
    }

    public RoomType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    
    public List<RoomRate> getRoomRateList() {
        return roomRateList;
    }

    public void setRoomTypeList(List<RoomRate> roomRateList) {
        this.roomRateList = roomRateList;
    }
    
    @Override
    public String toString() {
        return "progmatic.bookingmanager.RoomType[ id=" + id + " ]";
    }
    
}
