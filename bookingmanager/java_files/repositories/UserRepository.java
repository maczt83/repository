/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.User;

/**
 *
 * @author Stankye
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
}
