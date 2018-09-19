/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import progmatic.bookingmanager.databaseEntity.User;

/**
 *
 * @author Attila
 */
@Controller
public class LoginController {
    
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping( value = "/login", method = RequestMethod.GET)
    public String login(Model model, Principal user) {
        LOG.info("Login page request recived.");
        model.addAttribute("isLoggedin", ( !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser") ));     
        model.addAttribute("user", new User());
        return "login";
    }

}
