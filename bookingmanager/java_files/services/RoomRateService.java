package progmatic.bookingmanager.services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.RoomRate;
import progmatic.bookingmanager.databaseEntity.RoomType;
import progmatic.bookingmanager.repositories.RoomRateRepository;
import progmatic.bookingmanager.repositories.RoomTypeRepository;

@Service 
public class RoomRateService {
    
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    RoomRateRepository roomRateRepo;
    
    @Autowired
    RoomTypeRepository roomTypeRepo;
    
    
    public String errorMessageToRate(int rate){
        return (rate==0 ? "Please add the rate." : "");
    }
    
    public String errorMessageToDate(Date date, Long roomTypeId, Long roomRateId) {
        RoomType findById = roomTypeRepo.findById(roomTypeId);
        for (RoomRate roomrate : findById.getRoomRateList()){
            if ( roomRateId != roomrate.getId() && 
                  ( date.after(roomrate.getStartDate()) && date.before(roomrate.getEndDate()) ||
                    date.equals(roomrate.getStartDate()) || date.equals(roomrate.getEndDate()) ) ) {
                return "This season is already exist.";        
            }
        }
        return "";
    
    }
    
    public String datesAreValid(Date startDate, Date endDate) {
        return (startDate.before(endDate) ? "" : "Please give a valid start and end date."); 
    }
    
    
    @Transactional
    public void addRoomRate(RoomRate newRoomRate) {
        em.persist(newRoomRate);
    }
    
    @Transactional 
    public void deleteRoomRate(Long roomRateId) {
        roomRateRepo.delete(roomRateId);
    }
    
    @Transactional
    public void modifyRoomRate(RoomRate roomRate) {
        roomRateRepo.save(roomRate);
    }
    
    public RoomRate searchRoomRateById(Long roomRateId) {
        return roomRateRepo.findById(roomRateId);
    }
    
    public List<RoomRate> showAllRoomRate(){
        return roomRateRepo.findAll();
    }
}
