/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.passwordchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bence
 */
@Controller
public class PasswordChangeController {
    
    private static final Logger LOG = LoggerFactory.getLogger(PasswordChangeController.class);
    
    @Autowired
    PasswordChangeService pChService;

    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.GET)
    public String showPasswordChangeSite (Model model){
        model.addAttribute("changer", new PasswordChangerObject());
        return "password-change";
    }
    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.POST)
    public String changePassword (PasswordChangerObject changer,Model model){
        LOG.info("New pw: " + changer.getNewPassword()+" Old pw:"+changer.getOldPassword());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (pChService.checkOldPassword(changer.getOldPassword(),userName) && !changer.getNewPassword().equals("")){
            pChService.changeUsersPassword(changer.getNewPassword(),userName);
            return "redirect:/index";
        }else{
            model.addAttribute("changer", new PasswordChangerObject());
            return "password-change"; 
        }
    }
}
