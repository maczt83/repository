/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.util.List;

/**
 *
 * @author Stankye
 */
public class BuildingDto {
    
    private Integer id;
    
    private String title;
    
    private List<RoomCalendarDto> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RoomCalendarDto> getChildren() {
        return children;
    }

    public void setChildren(List<RoomCalendarDto> children) {
        this.children = children;
    }

}