package progmatic.bookingmanager.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.Reservation;
import progmatic.bookingmanager.databaseEntity.Room;
import progmatic.bookingmanager.dtos.ReservationDto;
import progmatic.bookingmanager.dtos.RoomReservationDto;
import progmatic.bookingmanager.repositories.BookingRepository;
import progmatic.bookingmanager.repositories.ReservationRepository;
import progmatic.bookingmanager.repositories.RoomRepository;

@Component
public class ReservationService {

    @Autowired
    ReservationRepository rRep;
    @Autowired
    BookingRepository bRep;
    @Autowired
    RoomRepository roomRepo;
    @PersistenceContext
    EntityManager em;

    public ReservationService() {
    }

    public List<Reservation> returnAllReservationList() {
        List<Reservation> reservationList = em.createQuery(
                "SELECT r FROM Reservation r")
                .getResultList();
        return reservationList;
    }

    public List<ReservationDto> getReservationsByBookingId(Booking booking){
        List <Reservation> bookingReservs = rRep.findByBookingId(booking);
        List<ReservationDto> reservDtosOfBooking = new ArrayList<>();
        for (Reservation reservation : bookingReservs) {
            ReservationDto actual = new ReservationDto();
            actual.setArrival(reservation.getCheckInTime());
            actual.setFrom(reservation.getStartDate());
            actual.setTo(reservation.getEndDate());
            actual.setRoomNumber(reservation.getRoomId().getRoomNumber());

            if(reservation.getBreakfast()==1){

                actual.setService("breakfast");
            }else{
                actual.setService("none");
            }
            actual.setNote(actual.getNote());
            reservDtosOfBooking.add(actual);
        }
        return reservDtosOfBooking;

    }

    public Booking findById(Long bookingId) {
        Booking findById = bRep.findById(bookingId);
        return findById;
    }

    
    public void createReservation(Reservation reservation){
        reservation.setPaid((short)0);
        rRep.save(reservation);
    }
    
   public List<RoomReservationDto> availableRooms(Date fromDate, Date toDate) {
        List<RoomReservationDto> rooms = new ArrayList<>();
        List<Object[]> resultList = em.createQuery("select room.id, room.roomNumber from Room room where room.roomNumber not in (select r.roomNumber from Room r join r.reservationList reservation where reservation.startDate between :fromDate and :toDate or reservation.endDate between :fromDate and :toDate)")
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();
        for (Object[] objects : resultList) {
            RoomReservationDto roomDto = new RoomReservationDto();
            Long id = (Long) objects[0];
            roomDto.setId(id);
            Integer roomNumber = (Integer) objects[1];
            roomDto.setRoomNumber(roomNumber);
            rooms.add(roomDto);
        }
        return rooms ;
    }

    public Reservation getSingleReservation(long resId) {
        Reservation reservation = (Reservation) em.createQuery(
                "SELECT r from Reservation r WHERE r.id = :resId")
                .setParameter("resId", resId)
                .getSingleResult();
        return reservation;
    }
    public void save(Reservation res){
        rRep.save(res);
    }

    public boolean isRoomAvailable(Long roomId, Date startDate, Date endDate) {
        List<RoomReservationDto> rooms= this.availableRooms(startDate, endDate);
        for (RoomReservationDto room : rooms) {
            if(room.getId()==roomId){
                return false;
            }
        }
        return true;
    }
    
}
