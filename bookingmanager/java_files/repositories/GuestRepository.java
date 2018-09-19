package progmatic.bookingmanager.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.Guest;


public interface GuestRepository extends JpaRepository<Guest, Long>{
    Guest findById(Long id);
    List<Guest> findAll();
    
    Guest findByEmailAddress(String email);
    
}
