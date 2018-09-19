package progmatic.bookingmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.Amenity;
import progmatic.bookingmanager.databaseEntity.Room;
import progmatic.bookingmanager.repositories.AmenityRepository;
import progmatic.bookingmanager.repositories.ReservationRepository;
import progmatic.bookingmanager.repositories.RoomRepository;
import progmatic.bookingmanager.repositories.RoomTypeRepository;

@Service
public class RoomService {

    private static final Logger LOG = LoggerFactory.getLogger(RoomService.class);

    
    @PersistenceContext
    EntityManager em;

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    RoomTypeRepository roomTypeRepo;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    ReservationRepository reservationRepo;

    @Autowired
    AmenityRepository amenityRepo;

    @Transactional
    public void addRoom(Room newRoom) {    
        List<Amenity> amenityList = newRoom.getAmenityList();
        for (Amenity amenity : amenityList) {
            amenity = em.merge(amenity);
            List<Room> roomList = amenity.getRoomList();
            if(roomList == null){
                roomList = new ArrayList<>();
                amenity.setRoomList(roomList);
            }
            roomList.add(newRoom);
        }
        em.persist(newRoom);
    }

    private boolean isAmenityListContainsRoom(Amenity amenity, Room room) {
        for (Room actRoom : amenity.getRoomList()) {
            if (actRoom.getId().equals(room.getId())) {
                return true;
            }
        }
        return false;
    }

    
    @Transactional
    public void modifyRoom(Room room) {
        Room managedRoom = em.find(Room.class, room.getId());
        List<Amenity> amenityList = room.getAmenityList();
        ListIterator<Amenity> li = managedRoom.getAmenityList().listIterator();
        while (li.hasNext()) {
            Amenity next = li.next();
            ListIterator<Room> amenityRoomList = next.getRoomList().listIterator();
            while(amenityRoomList.hasNext()) {
                Room actualRoom = amenityRoomList.next();
                if (Objects.equals(actualRoom.getId(), room.getId())) {
                    amenityRoomList.remove();
                }
                
            }         
        }
        for (Amenity amenity : amenityList) {
            if (! (isAmenityListContainsRoom(amenity, room))) {
                amenity = em.merge(amenity);
                List<Room> roomList = amenity.getRoomList();
                if(roomList == null){
                    roomList = new ArrayList<>();
                    amenity.setRoomList(roomList);
                }
                roomList.add(room);

            }
        }

        roomRepo.save(room);
    }

    @Transactional
    public void deleteRoom(long roomId) {
        List<Amenity> amenityList = roomRepo.findById(roomId).getAmenityList();
        for (Amenity amenity : amenityList) {
            List<Room> roomList = amenity.getRoomList();
            ListIterator<Room> listIterator = roomList.listIterator();
            while (listIterator.hasNext()) {
                Room next = listIterator.next();
                if(next.getId().equals(roomId)){
                    listIterator.remove();
                }               
            }
            
        }
        
        roomRepo.delete(roomId);
    }

    public Room searchRoomById(long roomId) {
        return roomRepo.findById(roomId);
    }

    
    private boolean firstCondition;
    
    private StringBuilder setStringBuilder(StringBuilder sb, Object parameter, String name){
        if (parameter != null){
            if(firstCondition){
                sb.append(" where ");
                firstCondition = false;
            }
            else{
                sb.append(" and ");
            }
            sb.append(" r." + name + "= :" + name.replaceAll("\\.", "_") + " ");
        }
        return sb;
    }
    
    private Query setQueryParameter(Query query, Object parameter, String name) {
        if (parameter != null) {
            query.setParameter(name.replaceAll("\\.", "_"), parameter);
        }        
        return query;
    }
    
    public List<Room> searchRoomByParameters(Integer roomNumber, Short capacity, Short floor, Short building, Long roomTypeId) {
        StringBuilder sb = new StringBuilder("select r from Room r");
        firstCondition=true;
        sb = setStringBuilder(sb, roomNumber, "roomNumber");
        sb = setStringBuilder(sb, capacity, "capacity");
        sb = setStringBuilder(sb, floor, "floor");
        sb = setStringBuilder(sb, building, "building");
        sb = setStringBuilder(sb, roomTypeId, "roomType.id");

        String sql = sb.toString();
        Query query = em.createQuery(sql);
        
        query = setQueryParameter(query, roomNumber, "roomNumber");
        query = setQueryParameter(query, capacity, "capacity");
        query = setQueryParameter(query, floor, "floor");
        query = setQueryParameter(query, building, "building");
        query = setQueryParameter(query, roomTypeId, "roomType.id");

        return query.getResultList();
    }


}
