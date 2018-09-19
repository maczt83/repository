package progmatic.bookingmanager.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import progmatic.bookingmanager.databaseEntity.RoomType;
import progmatic.bookingmanager.dtos.RoomTypeDto;
import progmatic.bookingmanager.services.RoomTypeService;

/**
 *
 * @author Máté
 */
@Controller
public class RoomTypeController {
    
    @Autowired
    private RoomTypeService roomTypeService;
   
    private final static String CREATE_ROOMTYPE_HEADING_TEXT = "Add new roomtype";
    private final static String MODIFY_ROOMTYPE_HEADING_TEXT = "Modify roomtype";
    private final static String CREATE_ROOMTYPE_URL = "/roomtype/create/";
    private final static String MODIFY_ROOMTYPE_URL = "/roomtype/modify/";
        
    private void setModel(Model model, String headingText, String url, RoomType roomtype) {
        model.addAttribute("headingText", headingText);
        model.addAttribute("url", url);
        model.addAttribute("roomTypeData", roomtype);
    }
    
       
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path="/roomtype/create", method = RequestMethod.GET)
    public String createRoomTypeGET(Model model) {
        
        setModel(model, CREATE_ROOMTYPE_HEADING_TEXT, CREATE_ROOMTYPE_URL, new RoomType());
        return "roomtype_new";
    }
    

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/roomtype/create", method = RequestMethod.POST)
    public String createRoomTypePOST(
            @Valid RoomType newRoomType, 
            BindingResult bindingResult,
            Model model) {
        
        String errorMessageToName = roomTypeService.errorMessageToName(newRoomType.getName(), newRoomType.getId());
        if (bindingResult.hasErrors()){
            setModel(model, CREATE_ROOMTYPE_HEADING_TEXT, CREATE_ROOMTYPE_URL, newRoomType);
            return "roomtype_new";
        } else if (!errorMessageToName.isEmpty()){
            setModel(model, CREATE_ROOMTYPE_HEADING_TEXT, CREATE_ROOMTYPE_URL, newRoomType);
            model.addAttribute("errorMessageRoomTypeName", errorMessageToName);
            return "roomtype_new";
        }
        roomTypeService.addRoomType(newRoomType);
        return "redirect:/roomtype/search";
    }
        

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path="/roomtype/modify/{roomTypeId}", method = RequestMethod.GET)
    public String modifyRoomTypeGET(@PathVariable("roomTypeId") Long roomTypeId, Model model){
        
        String url = MODIFY_ROOMTYPE_URL.concat(roomTypeId.toString());
        setModel(model, MODIFY_ROOMTYPE_HEADING_TEXT, url,  roomTypeService.searchRoomTypeById(roomTypeId));
        return "roomtype_new";   
    }
    

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path="/roomtype/modify/{roomTypeId}", method = RequestMethod.POST)
    public String modifyRoomTypePOST(
            @PathVariable("roomTypeId") Long roomTypeId, 
            @Valid @ModelAttribute("roomTypeData") RoomType roomtype,
            BindingResult bindingResult,
            Model model){
        
        String errorMessageToName = roomTypeService.errorMessageToName(roomtype.getName(), roomtype.getId());        
        String url = MODIFY_ROOMTYPE_URL.concat(roomTypeId.toString());

        if (bindingResult.hasErrors()){
            setModel(model, MODIFY_ROOMTYPE_HEADING_TEXT, url, roomtype);
            return "roomtype_new";
        }else if (!errorMessageToName.isEmpty()){
            setModel(model, MODIFY_ROOMTYPE_HEADING_TEXT, url, roomtype);
            model.addAttribute("errorMessageRoomTypeName", errorMessageToName);
            return "roomtype_new";
        }

        roomtype.setId(roomTypeId);
        roomTypeService.modifyRoomType(roomtype);
        return "redirect:/roomtype/search";   
    }
    

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path="/roomtype/delete/{roomTypeId}", method = RequestMethod.GET)
    public String deleteRoomType(@PathVariable ("roomTypeId") Long roomTypeId){
        roomTypeService.deleteRoomType(roomTypeId);
        return "redirect:/roomtype/search";
    }
    

    @RequestMapping(path="/roomtype/search", method = RequestMethod.GET)
    public String showAllRoomType(Model model) {
        List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
        List<RoomTypeDto> rtd = new ArrayList<>();
        for (RoomType roomType : roomTypeList) {
            rtd.add(new RoomTypeDto(roomType));
        }
        model.addAttribute("roomtypeList", rtd);
        return "roomtype_all";
    }
    

    @RequestMapping(path = "/roomtype/{roomtypeId}", method = RequestMethod.GET)
    public String showOneRoom(@PathVariable("roomtypeId") Long roomtypeId, Model model){
        
        model.addAttribute("roomtype",roomTypeService.searchRoomTypeById(roomtypeId));
        return "roomtype_showone";
    }

    
}
