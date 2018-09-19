package progmatic.bookingmanager.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType findById(Long id);
    List<RoomType> findAll();
    RoomType findByName(String name);

    
}
