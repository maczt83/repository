package progmatic.bookingmanager.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.Reservation;

/**
 *
 * @author Stankye
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    Reservation findById(long id);
    List <Reservation> findByBookingId(Booking booking);
    //List<Reservation> findByStartDateBetween(Date startDate);
}
