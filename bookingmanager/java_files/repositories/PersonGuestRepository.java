/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.Guest;
import progmatic.bookingmanager.databaseEntity.PersonGuest;

/**
 *
 * @author Anna
 */
public interface PersonGuestRepository extends JpaRepository<PersonGuest, Long> {

    List<PersonGuest> findByLastName(String guestName);
    List<PersonGuest> findByLastNameStartingWith(String fragment);
    List<PersonGuest> findByFirstNameStartingWith(String fragment);
    List<PersonGuest> findAllByOrderByLastNameAsc();
}
