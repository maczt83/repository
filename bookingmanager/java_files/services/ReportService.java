/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;
import progmatic.bookingmanager.databaseEntity.Guest;
import progmatic.bookingmanager.databaseEntity.PersonGuest;
import progmatic.bookingmanager.databaseEntity.Reservation;
import progmatic.bookingmanager.databaseEntity.Room;
import progmatic.bookingmanager.dtos.GuestDto;
import progmatic.bookingmanager.dtos.GuestReportDto;
import progmatic.bookingmanager.dtos.ArrivingLeavingGuests;
import progmatic.bookingmanager.dtos.RoomDto;

/**
 *
 * @author Anna
 */
@Service
public class ReportService {

    @PersistenceContext
    EntityManager em;

    /*SELECT guest.company_name, guest.last_name, guest.first_name, room.room_number, reservation.check_in_time 
    FROM 
    bookingmanager.reservation 
    JOIN bookingmanager.booking on reservation.booking_id = booking.id
    JOIN guest on booking.guest_id = guest.id
    JOIN room on reservation.room_id = room.id
    where reservation.start_date = "2018-06-15"
    ;*/
    public ArrivingLeavingGuests arrivingLeavingGuests(Date date) {
        ArrivingLeavingGuests guest = new ArrivingLeavingGuests();
        guest.setArrivingGuests(searchArrivingGuests(date));
        guest.setLeavingGuests(searchLeavingGuests(date));
        return guest;
    }

    public List<GuestReportDto> searchLeavingGuests(Date date) {
        List<GuestReportDto> leavingGuests = new ArrayList<>();
        List<Object[]> resultList = em.createQuery("select g, room.roomNumber, r.paid from Reservation r join r.bookingId b join b.guestId g join r.roomId room where r.endDate = :date")
                .setParameter("date", date)
                .getResultList();
        for (Object[] objects : resultList) {
            GuestReportDto guestDto = new GuestReportDto();
            Guest guest = (Guest) objects[0];
            if (guest instanceof PersonGuest) {
                PersonGuest pguest = (PersonGuest) guest;
                guestDto.setName(pguest.getLastName() + " " + pguest.getFirstName());
            } else {
                CompanyGuest cguest = (CompanyGuest) guest;
                guestDto.setName(cguest.getCompanyName());
            }
            Integer roomNumber = (Integer) objects[1];
            guestDto.setRoomNumber(roomNumber);
            Short paid = (Short) objects[2];
            guestDto.setPaid(paid);
            leavingGuests.add(guestDto);
        }
        return leavingGuests;
    }

    public List<GuestReportDto> searchArrivingGuests(Date date) {
        List<GuestReportDto> guests = new ArrayList<>();
        List<Object[]> resultList = em.createQuery("select g, room.roomNumber, r.checkInTime From Reservation r join r.bookingId b join b.guestId g join r.roomId room where r.startDate = :date")
                .setParameter("date", date)
                .getResultList();
        for (Object[] objects : resultList) {
            GuestReportDto guestDto = new GuestReportDto();
            Guest guest = (Guest) objects[0];
            if (guest instanceof PersonGuest) {
                PersonGuest pguest = (PersonGuest) guest;
                guestDto.setName(pguest.getLastName() + " " + pguest.getFirstName());
            } else {
                CompanyGuest cguest = (CompanyGuest) guest;
                guestDto.setName(cguest.getCompanyName());
            }
            Integer roomNumber = (Integer) objects[1];
            guestDto.setRoomNumber(roomNumber);
            Date checkInTime = (Date) objects[2];
            guestDto.setCheckInTime(checkInTime);
            guests.add(guestDto);
        }
        return guests;
    }

    /*SELECT * FROM bookingmanager.reservation
    join room on room.id = reservation.room_id 
    join room_type on room_type.id = room.room_type_id
    where "2018-06-15" NOT BETWEEN reservation.start_date and reservation.end_date 
    and "2018-06-14" NOT BETWEEN reservation.start_date and reservation.end_date;
     */
 /*select * from room
    where room_number not in (select room_number from room join reservation on room.id = reservation.room_id 
    where "2018-06-16"  BETWEEN reservation.start_date and reservation.end_date 
    or "2018-06-23"  BETWEEN reservation.start_date and reservation.end_date);*/
    public List<RoomDto> availableRooms(Date fromDate, Date toDate) {
        List<RoomDto> rooms = new ArrayList<>();
        List<Object[]> resultList = em.createQuery("select room.roomNumber, rType.name from Room room join room.roomType rType where room.roomNumber not in (select r.roomNumber from Room r join r.reservationList reservation where reservation.startDate between :fromDate and :toDate or reservation.endDate between :fromDate and :toDate)")
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();
        for (Object[] objects : resultList) {
            RoomDto roomDto = new RoomDto();
            Integer roomNumber = (Integer) objects[0];
            roomDto.setRoomNumber(roomNumber);
            String roomType = (String) objects[1];
            roomDto.setRoomType(roomType);
            rooms.add(roomDto);
        }
        return rooms ;
    }
    


/*SELECT * FROM bookingmanager.reservation right 
    join room on room.id = reservation.room_id 
    join room_type on room_type.id = room.room_type_id
    where reservation.start_date BETWEEN "2018-02-01" and "2018-03-21" 
    or  reservation.end_date BETWEEN "2018-02-01" and "2018-03-21";*/
public List<RoomDto> reservedRooms(Date fromDate, Date toDate) {
        List<RoomDto> reservedRooms = new ArrayList<>();
        List<Object[]> resultList = em.createQuery("select room.roomNumber, roomType.name, r.startDate, r.endDate from Reservation r join r.roomId room join room.roomType roomType where r.startDate between :fromDate and :toDate or r.endDate between :fromDate and :toDate")
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();
        for (Object[] objects : resultList) {
            Integer roomNumber = (Integer)objects[0];
            String roomType = (String)objects[1];
            Date startDate = (Date)objects[2];
            Date endDate = (Date)objects[3];
            RoomDto roomDto = new RoomDto();
            roomDto.setRoomNumber(roomNumber);
            roomDto.setRoomType(roomType);
            roomDto.setStartDate(startDate);
            roomDto.setEndDate(endDate);
            reservedRooms.add(roomDto);
        }
        return reservedRooms;
    }
    
    public boolean dateCustomError(Date date){
        if(date == null){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean invalidDateInterval(Date fromDate, Date toDate){
        if(fromDate.after(toDate)){
            return true;
        }else{
            return false;
        }
    }
}
