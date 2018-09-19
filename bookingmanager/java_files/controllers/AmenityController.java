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
import progmatic.bookingmanager.databaseEntity.Amenity;
import progmatic.bookingmanager.services.AmenityService;

@Controller
public class AmenityController {
    
    @Autowired
    private AmenityService amenityService;
    
    
    private static final String CREATE_AMENITY_HEADING_TEXT = "Add new amenity";
    private static final String MODIFY_AMENITY_HEADING_TEXT = "Modify amenity";
    private static final String CREATE_AMENITY_URL          = "/amenity/create/";
    private static final String MODIFY_AMENITY_URL          = "/amenity/modify/";
    
    
    private void setModel(Model model, String headingText, String url, Amenity amenity){
        model.addAttribute("headingText", headingText);
        model.addAttribute("url", url);
        model.addAttribute("amenityData", amenity);            
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/amenity/create", method = RequestMethod.GET)
    public String createAmenityGET(Model model) {

        setModel(model, CREATE_AMENITY_HEADING_TEXT, CREATE_AMENITY_URL, new Amenity());
        return "amenity_new";
    }
    

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/amenity/create", method = RequestMethod.POST)
    public String createAmenityPOST(
            @Valid @ModelAttribute("amenityData") Amenity newAmenity,
            BindingResult bindingResult,
            Model model) {
        
        String errorMessageToName = amenityService.errorMessageToName(newAmenity.getName(), newAmenity.getId());
        if (bindingResult.hasErrors()) {
            setModel(model, CREATE_AMENITY_HEADING_TEXT, CREATE_AMENITY_URL, newAmenity);
            model.addAttribute("errorMessageAmenityName", errorMessageToName);
            return "amenity_new";
        } else if (!errorMessageToName.isEmpty()) {
            setModel(model, CREATE_AMENITY_HEADING_TEXT, CREATE_AMENITY_URL, newAmenity);
            model.addAttribute("errorMessageAmenityName", errorMessageToName);
            return "amenity_new";
        
        }
        amenityService.addAmenity(newAmenity);
        return "redirect:/amenity/search";

    }
    

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/amenity/modify/{amenityId}", method = RequestMethod.GET)
    public String modifyAmenityGET(@PathVariable("amenityId") Long amenityId, Model model) {
    
        String url = MODIFY_AMENITY_URL.concat(amenityId.toString());
        setModel(model, MODIFY_AMENITY_HEADING_TEXT, url, amenityService.searchAmenityById(amenityId));
        return "amenity_new";
    }
    

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/amenity/modify/{amenityId}", method = RequestMethod.POST)
    public String modifyAmenityPOST(
            @PathVariable("amenityId") Long amenityId,
            @Valid @ModelAttribute("amenityData") Amenity amenity,
            BindingResult bindingResult,
            Model model) {

        String errorMessageToName = amenityService.errorMessageToName(amenity.getName(), amenity.getId());
        String url = MODIFY_AMENITY_URL.concat(amenityId.toString());
        
        if (bindingResult.hasErrors()) {
            setModel(model, MODIFY_AMENITY_HEADING_TEXT, url, amenity);
            return "amenity_new";
        } else if (!errorMessageToName.isEmpty()) {
            setModel(model, MODIFY_AMENITY_HEADING_TEXT, url, amenity);
            model.addAttribute("errorMessageAmenityName", errorMessageToName);
            return "amenity_new";
        
        }
        amenity.setId(amenityId);
        amenityService.modifyAmenity(amenity);
        return "redirect:/amenity/search";
    }
    
    
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/amenity/delete/{amenityId}", method = RequestMethod.GET)
    public String deleteAmenity(@PathVariable("amenityId") Long amenityId) {
        
        amenityService.deleteAmenity(amenityId);
        return "redirect:/amenity/search";
    }


    @RequestMapping(path = "/amenity/search", method = RequestMethod.GET)
    public String searchAmenities(Model model){
        
        model.addAttribute("amenityList", amenityService.findAllAmenity());
        return "amenity_all";
    }
    

}
