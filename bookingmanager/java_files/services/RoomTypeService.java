package progmatic.bookingmanager.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.RoomType;
import progmatic.bookingmanager.repositories.RoomTypeRepository;

/**
 *
 * @author Máté 
 */
@Service
public class RoomTypeService {
    
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    RoomTypeRepository roomTypeRepo;
   
    
    public String errorMessageToName(String newName, Long id) {
        RoomType findByName = roomTypeRepo.findByName(newName);
        RoomType findById = roomTypeRepo.findById(id);
        
        //return ( (findByName == null || findById.equals(findByName)) ? "" : "This roomtype is already exist." );
        return ( (findByName == null) ? "" : "This roomtype is already exist." );
    }
    
    @Transactional
    public void addRoomType (RoomType newRoomType) {
        em.persist(newRoomType);
    }
   
    public RoomType searchRoomTypeById(long roomTypeId) {
        return roomTypeRepo.findById(roomTypeId);
    }
    
    @Transactional
    public void deleteRoomType(long roomTypeId){
       roomTypeRepo.delete(roomTypeId);
    }
    
    @Transactional
    public void modifyRoomType(RoomType roomType){
       roomTypeRepo.save(roomType);
    }
    
    public List<RoomType> getRoomTypeList() {
       return roomTypeRepo.findAll();
    }
    
    
}
