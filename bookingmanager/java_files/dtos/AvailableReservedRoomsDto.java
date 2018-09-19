/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anna
 */
public class AvailableReservedRoomsDto {
    private String status;
    private List<String> errors = new ArrayList<>();
    private List<RoomDto> availableRooms = new ArrayList<>();
    private List<RoomDto> reveservedRooms = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public List<RoomDto> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(List<RoomDto> availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<RoomDto> getReveservedRooms() {
        return reveservedRooms;
    }

    public void setReveservedRooms(List<RoomDto> reveservedRooms) {
        this.reveservedRooms = reveservedRooms;
    }
    
    
}
