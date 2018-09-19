package progmatic.bookingmanager.controllers;

import java.util.List;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import progmatic.bookingmanager.databaseEntity.Guest;
import progmatic.bookingmanager.dtos.GuestDto;
import progmatic.bookingmanager.services.GuestsService;
import progmatic.bookingmanager.enums.GuestTitleEnum;

@Controller
public class GuestsController {
    
    @Autowired
    GuestsService guestsService;
    
    @RequestMapping(value = {"/guests"}, method = GET)
    public String showSearchPage () {
        return "guest_search";
    }
    //MEGJAVÍTANI
    @RequestMapping(value = {"/guests/search"}, method = GET)
    public @ResponseBody List<GuestDto> showSearchedGuests(@RequestParam String name) {
        return guestsService.findGuestsByFragmentsOfName(name);
    }
    
    @RequestMapping(value = {"/guests/showAll"}, method = GET)
    public @ResponseBody List<GuestDto> showAllGuests() {
        return guestsService.allGuestsAndCompanies();
    }
    
    @RequestMapping(value = {"/guests/newGuest"}, method = GET)
    public String addNewGuestGet (Model model) {
        model.addAttribute("enumValues",GuestTitleEnum.values());
        model.addAttribute("url", "/guests/newGuest");
        //model.addAttribute("guestData",new Guest());
        model.addAttribute("guestData",  new GuestDto());
        model.addAttribute("heading", "Add new guest");
        return "guest_guestdata";
    }

    //TODO missing redirection and chech the if statement!!CompanyName
    @RequestMapping(value = {"/guests/newGuest"}, method = POST)
    public String addNewGuestPut (@Valid @ModelAttribute("guestData") GuestDto newGuest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !addNewGuestCustomErrors(newGuest, bindingResult, model)) {
            model.addAttribute("enumValues",GuestTitleEnum.values());
            model.addAttribute("url", "/guests/newGuest");
            model.addAttribute("guestData",newGuest);
            model.addAttribute("heading", "Add new guest");
            return "guest_guestdata";
        }
        guestsService.addNewGuest(newGuest);
        return "redirect:/guests";
    }
    
     private boolean addNewGuestCustomErrors(GuestDto newGuest, BindingResult bindingResult, Model model) {
        boolean ok = true;
        if(newGuest.getTitle().equals("COMPANY")){
            if (guestsService.companyGuestCompanyNameError(newGuest)) {
            FieldError fe = new FieldError("guestData", "companyName", "Please provide a valid companyname.");
            bindingResult.addError(fe);
            ok = false;
            }
        }else{
            if (guestsService.personGuestFirstNameError(newGuest)) {
            FieldError fe = new FieldError("guestData", "firstName", "Please provide a valid firstname.");
            bindingResult.addError(fe);
            ok = false;
            }
            if (guestsService.personGuestLastNameError(newGuest)) {
                FieldError fe = new FieldError("guestData", "lastName", "Please provide a valid lastname.");
                bindingResult.addError(fe);
                ok = false;
            }
        }
        
        
        return ok;
    }
    
    @RequestMapping(value = {"/guests/{guestId}"}, method = GET)
    public String showOneGuestData (@PathVariable("guestId") Long id, Model model) {
        model.addAttribute("enumValues", GuestTitleEnum.values());
        model.addAttribute("url", "/guests/"+id);
        model.addAttribute("guestData",guestsService.findGuestDtoById(id));
        model.addAttribute("heading", "Modify guest data");
        return "guest_guestdata";
    }

    @RequestMapping(value = {"/guests/{guestId}"}, method = POST)
    public String modifyGuestData (@Valid @ModelAttribute("guestData") GuestDto guest,@PathVariable("guestId") Long id, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors() || !addNewGuestCustomErrors(guest, bindingResult, model)){
            model.addAttribute("enumValues", GuestTitleEnum.values());
            model.addAttribute("url", "/guests/"+id);
            model.addAttribute("guestData",guest);
            model.addAttribute("heading", "Modify guest data");
            return "guest_guestdata";
        }
        guest.setId(id);
        guestsService.modifyGuestData(guest);
        return "redirect:/guests";
    }
    
    @RequestMapping(value = {"/guests/checkEmail"}, method = GET)
    @ResponseBody
    public Boolean checkEmailAddress(@RequestParam String email) {
        return guestsService.checkEmailAddresses(email);
    }
    
}
