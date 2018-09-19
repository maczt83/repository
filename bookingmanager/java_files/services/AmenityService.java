package progmatic.bookingmanager.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.Amenity;
import progmatic.bookingmanager.repositories.AmenityRepository;

@Service
public class AmenityService {
    
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    AmenityRepository amenityRepo;
    
    public String errorMessageToName(String name, Long id) {
        Amenity findByName = amenityRepo.findByName(name);
        return (findByName == null ? "" : "This amenity is already exist.");
    }
    
    
    @Transactional
    public void addAmenity(Amenity newAmenity) {
        em.persist(newAmenity);
    }
    
    @Transactional
    public void modifyAmenity(Amenity amenity) {
        amenityRepo.save(amenity);
    }
    
    @Transactional
    public void deleteAmenity(long amenityId) {
        amenityRepo.delete(amenityId);
    }
    
    public Amenity searchAmenityById(long amenityId) {
        return amenityRepo.findById(amenityId);
    }
    
    public List<Amenity> findAllAmenity() {
        return amenityRepo.findAll();
    }
}
