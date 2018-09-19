/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

/**
 *
 * @author Attila
 */
@Entity
public class PasswordResetToken {
    
    private static final int EXPIRATION_TIME_IN_MINUTES = 60 * 24;
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private long id;
    
    private String token;
    
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn( nullable = false, name = "user_id" )
    private User user;
    
    private Date expiryDate;

    public PasswordResetToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }
    
    private Date calculateExpiryDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME_IN_MINUTES);
        return calendar.getTime();
    }

    public PasswordResetToken() {
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    
    
    
}
