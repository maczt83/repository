package progmatic.bookingmanager.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.repositories.BookingRepository;

@Component
public class BookingService {

    @PersistenceContext
    EntityManager em;
    @Autowired
    BookingRepository bRep;

    public BookingService() {
    }

    public List<Booking> returnBookingList() {
        List<Booking> bookingList = em.createQuery(
                "SELECT b FROM Booking b")
                .getResultList();
        return bookingList;
    }

    public long returnNumOfReservations(long bookingId) {
        long numOfRes = (long) em.createQuery(
                "SELECT COUNT(r) FROM Reservation r WHERE r.bookingId.id = :bookingId")
                .setParameter("bookingId", bookingId)
                .getSingleResult();
        return numOfRes;
    }
    public Booking getBookingById(Long id){
        return bRep.findById(id);
    }
    public void saveBooking (Booking booking){
        bRep.save(booking);
    }

}
