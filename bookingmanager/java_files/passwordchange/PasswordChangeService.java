/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.passwordchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import progmatic.bookingmanager.databaseEntity.User;
import progmatic.bookingmanager.repositories.UserRepository;

/**
 *
 * @author Bence
 */
@Component
public class PasswordChangeService {
    private static final Logger LOG = LoggerFactory.getLogger(PasswordChangeService.class);
    @Autowired
    UserRepository uRep;
    @Autowired
    PasswordEncoder encoder;
    public Boolean checkOldPassword (String oldPassword , String userName){
        User user = uRep.findByUsername(userName);
        return user != null && encoder.matches(oldPassword, user.getPassword()) ;
        
    }
    public void changeUsersPassword (String newPassword , String userName){
        User user = uRep.findByUsername(userName);
        String encryptedNewPassword = encoder.encode(newPassword);
        user.setPassword(encryptedNewPassword);
        uRep.save(user);
        LOG.info("password changed to: " + newPassword);
    }
    
    
}
