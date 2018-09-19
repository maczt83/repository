/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import progmatic.bookingmanager.services.CalendarService;

/**
 *
 * @author Attila
 */
@Controller
public class HomeController {
    
    @Autowired
    CalendarService calendarService;
    
    @RequestMapping( value = { "/" , "" , "/index" } )
    public String loadIndexPage(Model model){
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("buildingList", calendarService.findAllBuildingDto());
        model.addAttribute("reservationList", calendarService.findAllReservationDto());
        return "index";
    }
}
