/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.Booking;

/**
 *
 * @author Stankye
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findById(long id);
    
}
