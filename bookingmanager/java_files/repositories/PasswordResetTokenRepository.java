/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.PasswordResetToken;
import progmatic.bookingmanager.databaseEntity.User;

/**
 *
 * @author Attila
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
    
    
}
