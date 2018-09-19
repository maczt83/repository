package progmatic.bookingmanager.controllers;

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
import progmatic.bookingmanager.databaseEntity.RoomRate;
import progmatic.bookingmanager.databaseEntity.RoomType;
import progmatic.bookingmanager.services.RoomRateService;

@Controller
public class RoomRateController {

    @Autowired
    private RoomRateService roomRateService;
    
    private static final String CREATE_ROOMRATE_HEADING_TEXT = "Add new room rate";
    private static final String MODIFY_ROOMRATE_HEADING_TEXT = "Modify room rate";
    private static final String CREATE_ROOMRATE_URL          = "/roomrate/create/";
    private static final String MODIFY_ROOMRATE_URL          = "/roomrate/modify/";

    private void setModel(Model model, String headingText, String url, Long roomTypeId, RoomRate roomrate){
        model.addAttribute("headingText", headingText);
        model.addAttribute("url", url.concat(roomTypeId.toString()));
        model.addAttribute("roomrateData", roomrate);
    }
    
    private void setModelErrorMessage(Model model, String errorMessageToRate, String errorMessageToStartDate, String errorMessageToEndDate) {
        model.addAttribute("errorMessageRate", errorMessageToRate);
        model.addAttribute("errorMessageStartDate", errorMessageToStartDate);
        model.addAttribute("errorMessageEndDate", errorMessageToEndDate);
    }
    

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/roomrate/create/{roomtypeId}", method = RequestMethod.GET)
    public String createNewRoomRateGET(
            @PathVariable("roomtypeId") Long roomtypeId,
            Model model) {

        setModel(model, CREATE_ROOMRATE_HEADING_TEXT, CREATE_ROOMRATE_URL, roomtypeId, new RoomRate());
        return "roomRate_new";
        
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/roomrate/create/{roomtypeId}", method = RequestMethod.POST)
    public String createNewRoomRatePOST(
            @PathVariable("roomtypeId") Long roomtypeId,
            @Valid @ModelAttribute("roomrateData") RoomRate newRoomRate,
            BindingResult bindingResult,
            Model model) {

        String errorMessageToRate       = roomRateService.errorMessageToRate(newRoomRate.getRate());
        String errorMessageToStartDate  = roomRateService.errorMessageToDate(newRoomRate.getStartDate(), roomtypeId, (long)0);
        String errorMessageToEndDate    = roomRateService.errorMessageToDate(newRoomRate.getEndDate(), roomtypeId, (long)0);
        String errorMessageToDates      = roomRateService.datesAreValid(newRoomRate.getStartDate(), newRoomRate.getEndDate());
        
        if (bindingResult.hasErrors()) {
            setModel(model, CREATE_ROOMRATE_HEADING_TEXT, CREATE_ROOMRATE_URL, roomtypeId, newRoomRate);
            return "roomRate_new";    
            
        } else if ( !errorMessageToStartDate.isEmpty() || !errorMessageToEndDate.isEmpty() ) {
            setModel(model, CREATE_ROOMRATE_HEADING_TEXT, CREATE_ROOMRATE_URL, roomtypeId, newRoomRate);
            setModelErrorMessage(model, errorMessageToRate, errorMessageToStartDate, errorMessageToEndDate);
            return "roomRate_new";
            
        } else if ( !errorMessageToRate.isEmpty() || !errorMessageToDates.isEmpty()){
            setModel(model, CREATE_ROOMRATE_HEADING_TEXT, CREATE_ROOMRATE_URL, roomtypeId, newRoomRate);
            setModelErrorMessage(model, errorMessageToRate, errorMessageToDates, errorMessageToDates);
            return "roomRate_new";
            
        } else {
            RoomType roomtype = new RoomType(roomtypeId);
            newRoomRate.setRoomTypeid(roomtype);
            roomRateService.addRoomRate(newRoomRate);
            return "redirect:/roomtype/{roomtypeId}";
        }
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/roomrate/modify/{roomRateId}/{roomTypeId}", method = RequestMethod.GET)
    public String modifyRoomRateGET(
            @PathVariable("roomRateId") Long roomRateId,
            @PathVariable("roomTypeId") Long roomTypeId, 
            Model model) {

        String url = MODIFY_ROOMRATE_URL.concat(roomRateId.toString())+"/";
        setModel(model, MODIFY_ROOMRATE_HEADING_TEXT, url, roomTypeId, roomRateService.searchRoomRateById(roomRateId));
        return "roomRate_new";

    }

    
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/roomrate/modify/{roomRateId}/{roomTypeId}", method = RequestMethod.POST)
    public String modifyRoomRatePOST(
            @PathVariable("roomRateId") Long roomRateId,
            @PathVariable("roomTypeId") Long roomTypeId,
            @Valid @ModelAttribute("roomrateData") RoomRate roomrate,
            BindingResult bindingResult,
            Model model) {

               
        String errorMessageToRate       = roomRateService.errorMessageToRate(roomrate.getRate());
        String errorMessageToStartDate  = roomRateService.errorMessageToDate(roomrate.getStartDate(), roomTypeId, roomRateId);
        String errorMessageToEndDate    = roomRateService.errorMessageToDate(roomrate.getEndDate(), roomTypeId, roomRateId);
        String errorMessageToDates      = roomRateService.datesAreValid(roomrate.getStartDate(), roomrate.getEndDate());
        String url                      = MODIFY_ROOMRATE_URL.concat(roomRateId.toString())+"/";

        if (bindingResult.hasErrors()) {
            setModel(model, MODIFY_ROOMRATE_HEADING_TEXT, url, roomTypeId, roomrate);
            return "roomRate_new";

        } else if ( !errorMessageToStartDate.isEmpty() || !errorMessageToEndDate.isEmpty() ) {
            setModel(model, MODIFY_ROOMRATE_HEADING_TEXT, url, roomTypeId, roomrate);
            setModelErrorMessage(model, errorMessageToRate, errorMessageToStartDate, errorMessageToEndDate);
            return "roomRate_new";
            
        } else if (!errorMessageToRate.isEmpty() || !errorMessageToDates.isEmpty()){
            setModel(model, MODIFY_ROOMRATE_HEADING_TEXT, url, roomTypeId, roomrate);
            setModelErrorMessage(model, errorMessageToRate, errorMessageToDates, errorMessageToDates);
            return "roomRate_new";
            
        } else {
            RoomType roomtype = new RoomType(roomTypeId);
            roomrate.setRoomTypeid(roomtype);
            roomrate.setId(roomRateId);
            roomRateService.modifyRoomRate(roomrate);
            return "redirect:/roomtype/{roomTypeId}";
        }
 
    }

    
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/roomrate/delete/{roomRateId}/{roomTypeId}", method = RequestMethod.GET)
    public String deleteRoomRate(@PathVariable("roomRateId") Long roomRateId, @PathVariable("roomTypeId") Long roomTypeId) {

        roomRateService.deleteRoomRate(roomRateId);
        return "redirect:/roomtype/{roomTypeId}";

    }

    
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/roomrate/search", method = RequestMethod.GET)
    public String searchRoomRates(Model model) {
        
        model.addAttribute("roomRates", roomRateService.showAllRoomRate());
        return "roomRate_all";
        
    }
    
}
