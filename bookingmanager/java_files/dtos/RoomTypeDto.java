/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.util.ArrayList;
import java.util.List;
import progmatic.bookingmanager.databaseEntity.RoomRate;
import progmatic.bookingmanager.databaseEntity.RoomType;

/**
 *
 * @author User
 */
public class RoomTypeDto {
    private Long id;
    private String name;
    private List<RoomRateDto> roomRateList;
    private int roomRateSize;

    public int getRoomRateSize() {
        return roomRateSize;
    }

    public void setRoomRateSize(int roomRateSize) {
        this.roomRateSize = roomRateSize;
    }

    public RoomTypeDto() {
    }

    public RoomTypeDto(RoomType rt) {
        this.id = rt.getId();
        this.name = rt.getName();
        this.roomRateList = new ArrayList<>();
        List<RoomRate> roomRateList1 = rt.getRoomRateList();
        for (RoomRate roomRate : roomRateList1) {
            RoomRateDto rd = new RoomRateDto(roomRate);
            roomRateList.add(rd);
        }
        this.roomRateSize = roomRateList.size();
    }
    
    

    public Long getId() {
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

    public List<RoomRateDto> getRoomRateList() {
        return roomRateList;
    }

    public void setRoomRateList(List<RoomRateDto> roomRateList) {
        this.roomRateList = roomRateList;
    }
    
}
