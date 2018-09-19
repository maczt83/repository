/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;
import progmatic.bookingmanager.databaseEntity.PersonGuest;
import progmatic.bookingmanager.databaseEntity.Reservation;
import progmatic.bookingmanager.databaseEntity.Room;
import progmatic.bookingmanager.dtos.BuildingDto;
import progmatic.bookingmanager.dtos.ReservationCalendarDto;
import progmatic.bookingmanager.dtos.RoomCalendarDto;
import progmatic.bookingmanager.repositories.ReservationRepository;
import progmatic.bookingmanager.repositories.RoomRepository;

/**
 *
 * @author Stankye
 */
@Service
public class CalendarService {
    
    @Autowired
    RoomRepository roomRepo;
    
    @Autowired
    ReservationRepository resRepo;
    
    List<String> eventColorsList =new ArrayList<>(Arrays.asList("red", "yellow", "pink", "purple", "orange", "green", "blue", "brown", "grey"));
    Map<Integer, String> colorMap = new HashMap<>();
    
    @PostConstruct
    public void fillColorMap(){
        int counter = 0;
        for (int i = 1; i < 9000; i++) {
            colorMap.put(i-1, eventColorsList.get(counter));
            if(i%9 == 0){
                counter = 0;
            }else{
            counter++;
            }
        }
    }
    
    public List<ReservationCalendarDto> findAllReservationDto(){
        fillColorMap();
        List<Reservation> resList = resRepo.findAll();
        List<ReservationCalendarDto> resDtoList = new ArrayList();
        for (Reservation reservation : resList) {
            ReservationCalendarDto resDto = new ReservationCalendarDto();
            resDto.setId((int)reservation.getId());
            resDto.setResourceId((int)(long)reservation.getRoomId().getId());
            resDto.setStart(reservation.getStartDate());
            resDto.setEnd(reservation.getEndDate());
            if(reservation.getBookingId().getGuestId().getTitle().equals("COMPANY")){
                CompanyGuest cG = (CompanyGuest)reservation.getBookingId().getGuestId();
                resDto.setTitle(cG.getCompanyName());
            }else{
                PersonGuest pG = (PersonGuest)reservation.getBookingId().getGuestId();
                resDto.setTitle(pG.getFirstName() + " " + pG.getLastName());
            }
            resDto.setBookingId((int)(long)reservation.getBookingId().getId());
            resDto.setColor(colorMap.get((int)reservation.getBookingId().getId()));
            resDtoList.add(resDto);
            
        }
        return resDtoList;
    }
    
    public List<BuildingDto>  findAllBuildingDto(){
        List<Room> roomList = roomRepo.findAll();
        List<BuildingDto> buildingDtoList = new ArrayList();
        for (Room room : roomList) {
            BuildingDto buildingDto = new BuildingDto();
            buildingDto.setId((int) room.getBuilding());
            buildingDto.setTitle("Building " + room.getBuilding());
            List<RoomCalendarDto> rCDList = new ArrayList();
            for (int i = 0; i < roomList.size(); i++) {
                if((int)buildingDto.getId() == roomList.get(i).getBuilding()){
                    RoomCalendarDto rCD = new RoomCalendarDto();
                    rCD.setId((int)(long)roomList.get(i).getId());
                    rCD.setTitle("Room " + roomList.get(i).getRoomNumber());
                    rCDList.add(rCD);
                }
            }
            buildingDto.setChildren(rCDList);
            buildingDtoList.add(buildingDto);
        }
        return buildingDtoList;
    }
    
}
