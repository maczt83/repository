/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.controllers;

import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import progmatic.bookingmanager.databaseEntity.User;
import progmatic.bookingmanager.exceptions.PasswordResetTokenAlreadyExist;
import progmatic.bookingmanager.exceptions.PasswordResetTokenIsExpired;
import progmatic.bookingmanager.exceptions.PasswordResetTokenNotExist;
import progmatic.bookingmanager.passwordchange.PasswordChangeService;
import progmatic.bookingmanager.services.PasswordResetService;

/**
 *
 * @author Attila
 */
@Controller
public class PasswordResetController {
    
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetController.class);
    
    @Autowired
    PasswordResetService passwordResetService;
    
    @Autowired
    PasswordChangeService passwordChangeService;
    
    @RequestMapping( value = "/password_forgot", method = RequestMethod.GET )
    public String forgotPassword(Model model){        
        model.addAttribute("status", "ok");
        return "password_forgot";
    }
    
    @RequestMapping( value = "/password_forgot", method = RequestMethod.POST )
    public String resetEmailRequest(String emailAddress, Model model){
        LOG.info("Incoming password reset request to: " + emailAddress);
        try {
            passwordResetService.sendPasswordResetEmail(emailAddress);
            model.addAttribute("status", "emailSent");
        } catch (UsernameNotFoundException ex) {
            LOG.error(ex.getMessage());
            model.addAttribute("status", "noSuchUserError");
            model.addAttribute("msg", ex.getMessage());
        } catch (PasswordResetTokenAlreadyExist ex) {
            LOG.error(ex.getMessage());
            model.addAttribute("status", "tokenExistError");
            model.addAttribute("msg", ex.getMessage());
            model.addAttribute("emailAddressForResend", emailAddress);
        } catch (PasswordResetTokenIsExpired ex) {
            LOG.error(ex.getMessage());
            model.addAttribute("status", "tokenExpired");
            model.addAttribute("msg", "You have already requested a token, but it's expired. We sent you a new one");
        }
        return "password_forgot";
    }
    
    @RequestMapping( value = "/password_reset", method = RequestMethod.GET )
    public String resetPassword(
                @RequestParam( value = "token", required = true) String token, Model model){
        try {
            passwordResetService.validateResponseFromPasswordResetEmailLink(token);
            model.addAttribute("status", "ok");
            model.addAttribute("token", token);
        } catch (PasswordResetTokenNotExist ex) {
            LOG.error(ex.getMessage());
            model.addAttribute("status", "tokenNotExist");
            model.addAttribute("msg", ex.getMessage());
        } catch (PasswordResetTokenIsExpired ex) {
            LOG.error(ex.getMessage());
            model.addAttribute("status", "tokenExpired");
            model.addAttribute("msg", ex.getMessage());
        }
        return "password_reset";
    }
    
    @RequestMapping( value = "/password_reset", method = RequestMethod.POST )
    public String createNewPassword(
            @RequestParam( value = "inputPassword", required = true) String inputPassword, 
            @RequestParam( value = "inputPasswordAgain", required = true) String inputPasswordAgain,
            @RequestParam( value = "token", required = true) String token,
            Model model){
        
        try {
            passwordResetService.validateResponseFromPasswordResetEmailLink(token);
            model.addAttribute("status", "ok");
            model.addAttribute("token", token);
            User user = passwordResetService.getTheUserForThisToken(token);
            if (passwordResetService.checkTheTwoNewPasswordAreTheSame(inputPassword, inputPasswordAgain)){
                passwordChangeService.changeUsersPassword(inputPassword, user.getUsername());
                passwordResetService.loginTheUser(user);
                passwordResetService.deleteTokenFromDatabase(token);
                model.addAttribute("status", "passwordChangeSuccesful");
            }else{
                model.addAttribute("status", "notTheSame");
            }
        } catch (PasswordResetTokenNotExist ex) {
            LOG.error(ex.getMessage());
            model.addAttribute("status", "tokenNotExist");
            model.addAttribute("msg", ex.getMessage());
        } catch (PasswordResetTokenIsExpired ex) {
            LOG.error(ex.getMessage());
            model.addAttribute("status", "tokenExpired");
            model.addAttribute("msg", ex.getMessage());
        }
        
        return "password_reset";
    }
    
    @RequestMapping( value = "/resend_email", method = RequestMethod.GET )
    public String emailResend(
                @RequestParam( value = "email", required = true) String email, Model model){
        passwordResetService.resendPasswordResetEmail(email);
        model.addAttribute("status", "tokenExpired");
        model.addAttribute("msg", "We have resent you your password reset email to: "+email+ " Please check your inbox.");
        return "password_reset";
    }
    
}
