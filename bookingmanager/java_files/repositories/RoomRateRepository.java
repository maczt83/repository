/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.RoomRate;

/**
 *
 * @author Stankye
 */
public interface RoomRateRepository extends JpaRepository<RoomRate, Long>{
    RoomRate findById(long id);
    @Override
    List<RoomRate> findAll();
}
