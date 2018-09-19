package progmatic.bookingmanager.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;
import progmatic.bookingmanager.databaseEntity.PersonGuest;
import progmatic.bookingmanager.databaseEntity.Reservation;
import progmatic.bookingmanager.dtos.RoomReservationDto;
import progmatic.bookingmanager.reservation.EmailComparator;
import progmatic.bookingmanager.reservation.EndDateComparator;
import progmatic.bookingmanager.reservation.NameComparator;
import progmatic.bookingmanager.reservation.ReservationInfo;
import progmatic.bookingmanager.reservation.RoomTypeComparator;
import progmatic.bookingmanager.reservation.StartDateComparator;
import progmatic.bookingmanager.services.BookingService;
import progmatic.bookingmanager.services.ReservationService;

@Controller
public class ReservationController {

    @Autowired
    ReservationService resServ;

    @Autowired
    BookingService bookServ;

    private int[] searchParamCheck = {0, 0, 0, 0, 0};

    @RequestMapping(value = {"/reservationList"}, method = GET)
    @SuppressWarnings("empty-statement")
    public String listReservations(
            @RequestParam(name = "orderBy", required = false, defaultValue = "") String orderBy,
            Model model) {
        List<Reservation> reservations = resServ.returnAllReservationList();
        List<ReservationInfo> infoList = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationInfo resInfo = new ReservationInfo();
            String guestTitle = reservation.getBookingId().getGuestId().getTitle();
            if (guestTitle.equals("MALE") || guestTitle.equals("FEMALE")) {
                PersonGuest rGuest = (PersonGuest) reservation.getBookingId().getGuestId();
                String Name = rGuest.getLastName().concat(" ").concat(rGuest.getFirstName());
                resInfo.setStartDate(reservation.getStartDate());
                resInfo.setEndDate(reservation.getEndDate());
                resInfo.setName(Name);
                resInfo.setEmailAddress(rGuest.getEmailAddress());
                resInfo.setRoomTypeName(reservation.getRoomId().getRoomType().getName());
                resInfo.setId(reservation.getId());
            } else {
                CompanyGuest rGuest = (CompanyGuest) reservation.getBookingId().getGuestId();
                resInfo.setStartDate(reservation.getStartDate());
                resInfo.setEndDate(reservation.getEndDate());
                resInfo.setName(rGuest.getCompanyName());
                resInfo.setEmailAddress(rGuest.getEmailAddress());
                resInfo.setRoomTypeName(reservation.getRoomId().getRoomType().getName());
                resInfo.setId(reservation.getId());
            }
            infoList.add(resInfo);
        }
        if (!orderBy.equals("")) {
            infoList = searchParamSelector(orderBy, infoList);
        }
        model.addAttribute("infoList", infoList);
        return "reservationList";
    }

    public List<ReservationInfo> searchParamSelector(String orderBy, List<ReservationInfo> infoList) {

        switch (orderBy) {
            case "startDate":
                Collections.sort(infoList, new StartDateComparator());
                if (searchParamCheck[0] == 1) {
                    Collections.reverse(infoList);
                    Arrays.fill(searchParamCheck, 0);
                } else {
                    searchParamCheck[0] = 1;
                }
                break;
            case "endDate":
                Collections.sort(infoList, new EndDateComparator());
                if (searchParamCheck[1] == 1) {
                    Collections.reverse(infoList);
                    Arrays.fill(searchParamCheck, 0);
                } else {
                    searchParamCheck[1] = 1;
                }
                break;
            case "name":
                Collections.sort(infoList, new NameComparator());
                if (searchParamCheck[2] == 1) {
                    Collections.reverse(infoList);
                    Arrays.fill(searchParamCheck, 0);
                } else {
                    searchParamCheck[2] = 1;
                }
                break;
            case "emailAddress":
                Collections.sort(infoList, new EmailComparator());
                if (searchParamCheck[3] == 1) {
                    Collections.reverse(infoList);
                    Arrays.fill(searchParamCheck, 0);
                } else {
                    searchParamCheck[3] = 1;
                }
                break;
            case "roomTypeName":
                Collections.sort(infoList, new RoomTypeComparator());
                if (searchParamCheck[4] == 1) {
                    Collections.reverse(infoList);
                    Arrays.fill(searchParamCheck, 0);
                } else {
                    searchParamCheck[4] = 1;
                }
                break;
        }
        return infoList;
    }
    @RequestMapping(path = "/firstReservation/{guestId}", method = RequestMethod.GET)
    public String showCreateReservationGuestId(@PathVariable("guestId") Long guestId, Model model) {
        
        Reservation reservation = new Reservation();
        reservation.setPaid((short)1);
        //reservation.setBookingId(findById);
        model.addAttribute("headingText", "Add new reservation");
        model.addAttribute("reservationData", reservation);
        model.addAttribute("postLink", "/booking/new/"+guestId);
        return "reservation_new";

    }
    @RequestMapping(path = "/addReservation/{bookingId}", method = RequestMethod.GET)
    public String showCreateReservationBookingId(@PathVariable("bookingId") Long bookingId, Model model) {
        Booking findById = resServ.findById(bookingId);
        Reservation reservation = new Reservation();
        reservation.setPaid((short)1);
        reservation.setBookingId(findById);
        model.addAttribute("headingText", "Add new reservation");
        model.addAttribute("reservationData", reservation);
        model.addAttribute("postLink", "/booking/"+bookingId);
        return "reservation_new";

    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(path = "/reservations/create/{bookingId}", method = RequestMethod.POST)
    public String createNewReservation(
            @Valid @ModelAttribute("reservationData") Reservation newReservation,
            BindingResult bindingResult,
            @PathVariable("bookingId") Long bookingId,
            Model model) {        
        if (bindingResult.hasErrors()) {
            model.addAttribute("heading-text", "Add new reservation");
            model.addAttribute("reservationData", newReservation);
            return "reservation_new";
        } else {
           // newReservation.setBookingId(new Booking(bookingId));
            resServ.createReservation(newReservation);
            return "redirect:/reservationList";
       }
        
    }

    @RequestMapping(value = {"/reservation/{reservationId}"}, method = GET)
    public String createBooking(@PathVariable("reservationId") Long reservationId, Model model) {
        Reservation reservation = resServ.getSingleReservation(reservationId);
        ReservationInfo resInfo = new ReservationInfo();
        String guestTitle = reservation.getBookingId().getGuestId().getTitle();
        if (guestTitle.equals("MALE") || guestTitle.equals("FEMALE")) {
            PersonGuest rGuest = (PersonGuest) reservation.getBookingId().getGuestId();
            String Name = rGuest.getLastName().concat(" ").concat(rGuest.getFirstName());
            resInfo.setStartDate(reservation.getStartDate());
            resInfo.setEndDate(reservation.getEndDate());
            resInfo.setName(Name);
            resInfo.setEmailAddress(rGuest.getEmailAddress());
            resInfo.setRoomTypeName(reservation.getRoomId().getRoomType().getName());
            resInfo.setArrival(reservation.getCheckInTime());
            resInfo.setPhoneNumber(rGuest.getPhoneNumber());
            resInfo.setRoomNumber(reservation.getRoomId().getRoomNumber());
            if(reservation.getNote() != null) {
                resInfo.setNote(reservation.getNote());
            } else {
                resInfo.setNote("");
            }
            if (reservation.getBreakfast() == 0) {
                resInfo.setService("none");
            } else {
                resInfo.setService("breakfast");
            }
        } else {
            CompanyGuest rGuest = (CompanyGuest) reservation.getBookingId().getGuestId();
            resInfo.setStartDate(reservation.getStartDate());
            resInfo.setEndDate(reservation.getEndDate());
            resInfo.setName(rGuest.getCompanyName());
            resInfo.setEmailAddress(rGuest.getEmailAddress());
            resInfo.setRoomTypeName(reservation.getRoomId().getRoomType().getName());
            resInfo.setArrival(reservation.getCheckInTime());
            resInfo.setPhoneNumber(rGuest.getPhoneNumber());
            resInfo.setRoomNumber(reservation.getRoomId().getRoomNumber());
            if(reservation.getNote() != null) {
                resInfo.setNote(reservation.getNote());
            } else {
                resInfo.setNote("");
            }
            if (reservation.getBreakfast() == 0) {
                resInfo.setService("none");
            } else {
                resInfo.setService("breakfast");
            }
        }
        model.addAttribute("resInfo", resInfo);
        return "singleReservationView";
    }
    @RequestMapping(path = "/reservation/room", method = RequestMethod.GET)
    public @ResponseBody List<RoomReservationDto> showAvaiableRooms (@RequestParam String fromDate,@RequestParam String toDate){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate fromDateTime = LocalDate.parse(fromDate, formatter);
        Date roomFromDate=java.sql.Date.valueOf(fromDateTime);
        LocalDate toDateTime = LocalDate.parse(toDate, formatter);
        Date roomToDate=java.sql.Date.valueOf(toDateTime);
        return resServ.availableRooms(roomFromDate, roomToDate);
    }
}
