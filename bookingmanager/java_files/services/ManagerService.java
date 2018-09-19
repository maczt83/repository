/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.User;
import progmatic.bookingmanager.repositories.UserRepository;

/**
 *
 * @author Stankye
 */
@Service
public class ManagerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder pe;

    public boolean checkTheTwoPasswordAreTheSame(String pw1, String pw2) {
        return pw1.equals(pw2);
    }

    public boolean usernameIsAvalible(String username) {
        boolean isAvalible = true;
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                isAvalible = false;
            }
        }
        return isAvalible;
    }

    @Transactional
    public User addNewUser(User newUser) {
        em.persist(newUser);
        em.flush();
        return newUser;
    }

    @Transactional
    public User modifyUser(User user, long id) {
        user.setPassword(pe.encode(userRepository.findById(id).getPassword()));
        user.setUsername(userRepository.findById(id).getUsername());
        user.setActive((short) 1);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void setUserActiveOrInactive(long id) {
        User user = userRepository.findById(id);
        if (user.getActive() == 0) {
            user.setActive((short) 1);
        } else {
            user.setActive((short) 0);
        }
        userRepository.save(user);
    }
}
