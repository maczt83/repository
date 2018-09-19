/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import progmatic.bookingmanager.databaseEntity.User;
import progmatic.bookingmanager.repositories.UserRepository;

/**
 *
 * @author Stankye
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository uR;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =uR.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user like this");
        }
        return user;

    }
}