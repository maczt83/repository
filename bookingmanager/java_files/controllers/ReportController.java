/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import progmatic.bookingmanager.dtos.ArrivingLeavingGuests;
import progmatic.bookingmanager.dtos.AvailableReservedRoomsDto;
import progmatic.bookingmanager.dtos.RoomDto;
import progmatic.bookingmanager.services.ReportService;

/**
 *
 * @author Anna
 */
@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    //Megjeleníti azt a html-t, amelyiken van egy input ahová megadhatjuk a dátumot
    @RequestMapping(path = "/report/guests", method = RequestMethod.GET)
    public String showGuestReportPage() {
        return "guest_report";
    }
    
    @RequestMapping(path = "/report/rooms", method = RequestMethod.GET)
    public String showRoomReportPage() {
        return "room_report";
    }

//    @RequestMapping(path ="/arrivingGuests/date", method = RequestMethod.GET)
//    public @ResponseBody List<GuestReportDto> showGuests(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
//        return reportService.searchArrivingGuests(date);
//    }
//    
//    @RequestMapping(path = "/leavingGuests/date", method = RequestMethod.GET)
//    public @ResponseBody List<GuestReportDto> showLeavingGuests(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")Date date){
//        return reportService.searchLeavingGuests(date);
//    }
    @RequestMapping(path = "/report/date", method = RequestMethod.GET)
    public @ResponseBody ArrivingLeavingGuests showLeavingGuests(@RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        Date guestDate =java.sql.Date.valueOf(dateTime);
        return reportService.arrivingLeavingGuests(guestDate);
    }
    
    
    
    @RequestMapping(path="report/room", method = RequestMethod.GET)
    public @ResponseBody AvailableReservedRoomsDto showAvailableRooms(@RequestParam String fromDate, @RequestParam String toDate){
        AvailableReservedRoomsDto allRooms = new AvailableReservedRoomsDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate fromDateTime = LocalDate.parse(fromDate, formatter);
        Date roomFromDate =java.sql.Date.valueOf(fromDateTime);
        LocalDate toDateTime = LocalDate.parse(toDate, formatter);
        Date roomToDate = java.sql.Date.valueOf(toDateTime);
        
        if(reportService.dateCustomError(roomFromDate)){
            allRooms.setStatus("FAIL");
            allRooms.getErrors().add("Please enter an end date");
            return allRooms;
        }
        if(reportService.dateCustomError(roomToDate)){
            allRooms.setStatus("FAIL");
            allRooms.getErrors().add("Please enter a start date");
            return allRooms;
        }
        if(reportService.invalidDateInterval(roomFromDate, roomToDate)){
            allRooms.setStatus("FAIL");
            allRooms.getErrors().add("Please provide a valid date.");
            return allRooms;
        }
        allRooms.setStatus("SUCCESS");
        List<RoomDto> reservedRooms = new ArrayList<>();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        boolean isManager = false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_MANAGER".equals(auth.getAuthority())){
                isManager = true;
            }
        }
        if (isManager) {
            reservedRooms = reportService.reservedRooms(roomFromDate, roomToDate);
        }
        List<RoomDto> availableRooms = reportService.availableRooms(roomFromDate, roomToDate);
        
        allRooms.setAvailableRooms(availableRooms);
        allRooms.setReveservedRooms(reservedRooms);
        return allRooms;
    }

}
