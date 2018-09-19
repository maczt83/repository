package progmatic.bookingmanager.controllers;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import progmatic.bookingmanager.databaseEntity.Room;
import progmatic.bookingmanager.services.AmenityService;
import progmatic.bookingmanager.services.RoomService;
import progmatic.bookingmanager.services.RoomTypeService;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private AmenityService amenityService;
    
    
    private static final String CREATE_ROOM_HEADING_TEXT = "Add new room";
    private static final String MODIFY_ROOM_HEADING_TEXT = "Modify room";
    private static final String CREATE_ROOM_URL = "/room/create/";
    private static final String MODIFY_ROOM_URL = "/room/modify/";
    
    private void setModel(Model model, String headingText, String url, Room room){
        model.addAttribute("headingText", headingText);
        model.addAttribute("url", url);
        model.addAttribute("roomData", room);
        model.addAttribute("roomtypes", roomTypeService.getRoomTypeList());
        model.addAttribute("amenities", amenityService.findAllAmenity());
    }
    
    
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/room/create", method = RequestMethod.GET)
    public String createRoomGET(Model model) {
        
        Room newRoom = new Room();
        newRoom.setExtraBed((short)-1);
        setModel(model, CREATE_ROOM_HEADING_TEXT, CREATE_ROOM_URL, newRoom);
        return "room_new";
        
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/room/create", method = RequestMethod.POST)
    public String createRoomPOST(
            @Valid @ModelAttribute("roomData") Room newRoom,
            BindingResult bindingResult, 
            Model model) {
        
        if (bindingResult.hasErrors()) {
            setModel(model, CREATE_ROOM_HEADING_TEXT, CREATE_ROOM_URL, newRoom);
            return "room_new";
        } else {
            roomService.addRoom(newRoom);
            return "redirect:/room/search";
        }
        
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/room/modify/{roomId}", method = RequestMethod.GET)
    public String modifyRoomGET(@PathVariable("roomId") Long roomId, Model model) {
        
        String url = MODIFY_ROOM_URL.concat(roomId.toString());
        setModel(model, MODIFY_ROOM_HEADING_TEXT, url, roomService.searchRoomById(roomId));
        return "room_new";
        
    }
        
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/room/modify/{roomId}", method = RequestMethod.POST)
    public String modifyRoomPOST(
            @PathVariable("roomId") Long roomId, 
            @Valid @ModelAttribute("roomData") Room room,
            BindingResult bindingResult, 
            Model model) {
        
        String url = MODIFY_ROOM_URL.concat(roomId.toString());

        if (bindingResult.hasErrors()) {
            setModel(model, MODIFY_ROOM_HEADING_TEXT, url, roomService.searchRoomById(roomId));
            return "room_new";
        }
        room.setId(roomId);
        roomService.modifyRoom(room);
        return "redirect:/room/{roomId}";
        
    }
    
    
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/room/delete/{roomId}", method = RequestMethod.GET)
    public String deleteRoom(@PathVariable("roomId") Long roomId) {
        
        roomService.deleteRoom(roomId);
        return "redirect:/room/search";
        
    }
    
    @RequestMapping(path = "/room/search", method = RequestMethod.GET)
    public String searchRooms(
            @RequestParam(name = "capacity", required = false) Short capacity,
            @RequestParam(name = "building", required = false) Short building,
            @RequestParam(name = "floor", required = false) Short floor,
            @RequestParam(name = "roomNumber", required = false) Integer roomNumber,    
            @RequestParam(name = "roomType", required = false) Long roomTypeId, 
            Model model) {
        
        model.addAttribute("roomList", roomService.searchRoomByParameters(roomNumber, capacity, floor, building, roomTypeId));
        model.addAttribute("roomTypes", roomTypeService.getRoomTypeList());
        return "room_all";
        
    }

    
    @RequestMapping(path = "/room/{roomId}", method = RequestMethod.GET)
    public String showOneRoom (@PathVariable("roomId") Long roomId, Model model){
        
        model.addAttribute("room",roomService.searchRoomById(roomId));
        return "room_showone";
    }   


}
