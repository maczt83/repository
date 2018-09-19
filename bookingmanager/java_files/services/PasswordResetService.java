/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import java.util.ArrayList;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import progmatic.bookingmanager.databaseEntity.EmailTemplate;
import progmatic.bookingmanager.databaseEntity.PasswordResetToken;
import progmatic.bookingmanager.databaseEntity.User;
import progmatic.bookingmanager.exceptions.PasswordResetTokenAlreadyExist;
import progmatic.bookingmanager.exceptions.PasswordResetTokenIsExpired;
import progmatic.bookingmanager.exceptions.PasswordResetTokenNotExist;
import progmatic.bookingmanager.repositories.EmailTemplateRepository;
import progmatic.bookingmanager.repositories.PasswordResetTokenRepository;
import progmatic.bookingmanager.repositories.UserRepository;

/**
 *
 * @author Attila
 */
@Service
public class PasswordResetService {
    
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetService.class);
    
    @Autowired
    UserRepository uR;
    
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    EmailTemplateRepository emailTemplateRepository;
    
    public void sendPasswordResetEmail(String email) throws UsernameNotFoundException, PasswordResetTokenAlreadyExist, PasswordResetTokenIsExpired{
        User user = findUserByEmailAddress(email);        
        checkIfTokenExistOrNot(user, email);
        PasswordResetToken token = createNewToken(user);
        sendEmailWithToken(token);
    }
    
    private User findUserByEmailAddress(String email) throws UsernameNotFoundException{
        User user = uR.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Sorry, we can't find a Booking Manager account connected to this email: " + email);         
        }
        return user;
    }
    
    private void checkIfTokenExistOrNot(User user, String email) throws PasswordResetTokenAlreadyExist, PasswordResetTokenIsExpired{
        PasswordResetToken token = passwordResetTokenRepository.findByUser(user);
        if (token != null){
            try {
                checkIfTokenIsNotExpired(token);                
            } catch (PasswordResetTokenIsExpired ex) {
                resendExpiredTokenEmail(token, user);
                throw new PasswordResetTokenIsExpired("Your link is already expired.");
            }
            throw new PasswordResetTokenAlreadyExist("You have already requested a password reset to this email address: "+email+"! Please check your spam folder if you can't find it.");
        }
    }
    
    private PasswordResetToken createNewToken(User user){
        PasswordResetToken token = new PasswordResetToken(user);
        passwordResetTokenRepository.save(token); 
        return token;
    }
    
    private void sendEmailWithToken(PasswordResetToken token){
        Mail mail = new Mail();
        mail.setTo(token.getUser().getEmail());
        EmailTemplate emailTemplate = emailTemplateRepository.findById(1);
        mail.setSubject(emailTemplate.getSubject());
        String html = emailTemplate.getHtml().replaceAll("%passwordResetToken%", token.getToken());
        html = html.replaceAll("%nameOfUser%", token.getUser().getFirstName());
        mail.setText(html);
        emailService.sendMail(mail);
    }
    
    public void validateResponseFromPasswordResetEmailLink(String token) throws PasswordResetTokenNotExist, PasswordResetTokenIsExpired{
        PasswordResetToken passwordResetToken = findPasswordResetTokenByTokenString(token);
        checkIfTokenIsNotExpired(passwordResetToken);
    }
    
    public PasswordResetToken findPasswordResetTokenByTokenString(String token) throws PasswordResetTokenNotExist{
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null){
            throw new PasswordResetTokenNotExist("Token is not exist: " + token);
        }
        return passwordResetToken;
    }
    
    private void checkIfTokenIsNotExpired(PasswordResetToken passwordResetToken) throws PasswordResetTokenIsExpired{
        Calendar calendar = Calendar.getInstance();
        LOG.info("token expire time: " + passwordResetToken.getExpiryDate().getTime());
        LOG.info("system time: " + calendar.getTime().getTime());        
        if (passwordResetToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0){
            throw new PasswordResetTokenIsExpired("Your link is already expired.");
        }
    }
    
    public User getTheUserForThisToken(String token) throws PasswordResetTokenNotExist{
        PasswordResetToken passwordResetToken = findPasswordResetTokenByTokenString(token);
        User user = passwordResetToken.getUser();
        return user;     
    }
    
    public boolean checkTheTwoNewPasswordAreTheSame(String pw1, String pw2){
        return pw1.equals(pw2);
    }
    
    public void loginTheUser(User user){
        Authentication auth =  new UsernamePasswordAuthenticationToken(user, null, new ArrayList<GrantedAuthority>());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    
    public void resendExpiredTokenEmail(PasswordResetToken token, User user){
        passwordResetTokenRepository.delete(token);
        token = createNewToken(user);
        sendEmailWithToken(token);
    }
    
    public void deleteTokenFromDatabase(String token) throws PasswordResetTokenNotExist{
        PasswordResetToken passwrordResetToken = findPasswordResetTokenByTokenString(token);
        passwordResetTokenRepository.delete(passwrordResetToken);
    }
    
    public void resendPasswordResetEmail(String email){
        User user = findUserByEmailAddress(email); 
        PasswordResetToken token = passwordResetTokenRepository.findByUser(user);
        sendEmailWithToken(token);
    }
}
