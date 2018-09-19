package progmatic.bookingmanager.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;
import progmatic.bookingmanager.databaseEntity.PersonGuest;
import progmatic.bookingmanager.booking.BookingInfo;
import progmatic.bookingmanager.booking.CountryComparator;
import progmatic.bookingmanager.booking.ResNumberComparator;
import progmatic.bookingmanager.services.BookingService;
import java.util.Arrays;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import progmatic.bookingmanager.booking.EmailComparator;
import progmatic.bookingmanager.booking.NameComparator;
import progmatic.bookingmanager.databaseEntity.Reservation;
import progmatic.bookingmanager.dtos.GuestBookingViewDto;
import progmatic.bookingmanager.dtos.ReservationDto;
import progmatic.bookingmanager.services.BookingViewGuestService;
import progmatic.bookingmanager.services.GuestsService;
import progmatic.bookingmanager.services.ReservationService;

@Controller
public class BookingController {

    @Autowired
    BookingService bookServ;
    @Autowired
    BookingViewGuestService bVGService;
    @Autowired
    ReservationService reservService;
    @Autowired
    GuestsService guestService;

    private int[] searchParamCheck = {0, 0, 0, 0};
    
    @RequestMapping(value = {"/booking/new/{guestId}"}, method = GET)
    public String bookingPreview (@PathVariable("guestId")Long guestId, Model model){
        Booking booking = new Booking();
        booking.setGuestId(guestService.findGuestById(guestId));
        bookServ.saveBooking(booking);
        List <ReservationDto> reservations = reservService.getReservationsByBookingId(booking);
        GuestBookingViewDto guestData = bVGService.getBookingViewDto(booking);
        model.addAttribute("reservations", reservations);
        model.addAttribute("guest", guestData );
        model.addAttribute("bookingId", booking.getId());
        model.addAttribute("reservCreationLink","/firstReservation/"+guestId);
        return "singleBookingView";
    }
    @RequestMapping(value = {"/booking/new/{guestId}"}, method = RequestMethod.POST)
    public String createBooking (@PathVariable("guestId")Long guestId,
            @ModelAttribute("reservation")Reservation reservation,Model model){
       
        Booking booking = new Booking();
        booking.setGuestId(guestService.findGuestById(guestId));
        booking.addReservation(reservation);
        bookServ.saveBooking(booking);
        
        List <ReservationDto> reservations = reservService.getReservationsByBookingId(booking);
        GuestBookingViewDto guestData = bVGService.getBookingViewDto(booking);
        model.addAttribute("reservations", reservations);
        model.addAttribute("guest", guestData );
        model.addAttribute("bookingId", booking.getId());
        model.addAttribute("reservCreationLink","/addReservation/"+booking.getId());
        return "singleBookingView";
    }

    @RequestMapping(value = {"/booking/{bookingId}"}, method = GET)
    public String showBooking(@PathVariable("bookingId")Long bookingId, Model model){
        Booking booking = bookServ.getBookingById(bookingId);
        List <ReservationDto> reservations = reservService.getReservationsByBookingId(booking);
        GuestBookingViewDto guestData = bVGService.getBookingViewDto(booking);
        model.addAttribute("reservations", reservations);
        model.addAttribute("guest", guestData );
        model.addAttribute("bookingId", bookingId);
        model.addAttribute("reservCreationLink","/addReservation/"+bookingId);
        return "singleBookingView";
    }
    @RequestMapping(value = {"/booking/{bookingId}"}, method = RequestMethod.POST)
    public String addedBooking(@PathVariable("bookingId")Long bookingId, Model model,
            @ModelAttribute("reservation")Reservation reservation){
        
        Booking booking = bookServ.getBookingById(bookingId);
        if (reservService.isRoomAvailable(reservation.getRoomId().getId(), reservation.getStartDate(), reservation.getEndDate())) {
            booking.addReservation(reservation);
        }
        bookServ.saveBooking(booking);
        
        List <ReservationDto> reservations = reservService.getReservationsByBookingId(booking);
        GuestBookingViewDto guestData = bVGService.getBookingViewDto(booking);
        model.addAttribute("reservations", reservations);
        model.addAttribute("guest", guestData );

        model.addAttribute("bookingId", bookingId);
        model.addAttribute("reservCreationLink","/addReservation/"+bookingId);
        return "singleBookingView";
    }
    @RequestMapping(value = {"/bookingList"}, method = GET)
    @SuppressWarnings("empty-statement")
    public String listBookings(
            @RequestParam(name = "orderBy", required = false, defaultValue = "") String orderBy,
            Model model) {
        List<Booking> bookings = bookServ.returnBookingList();
        List<BookingInfo> infoList = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingInfo bookingInfo = new BookingInfo();
            if (booking.getGuestId().getTitle().equals("MALE") || booking.getGuestId().getTitle().equals("FEMALE")) {
                PersonGuest bGuest = (PersonGuest) booking.getGuestId();
                String Name = bGuest.getLastName().concat(" ").concat(bGuest.getFirstName());
                bookingInfo.setId(booking.getId());
                bookingInfo.setName(Name);
                bookingInfo.setEmailAddress(bGuest.getEmailAddress());
                bookingInfo.setCountry(bGuest.getCountry());
                bookingInfo.setNumOfRes(bookServ.returnNumOfReservations(booking.getId()));
            } else {
                CompanyGuest bGuest = (CompanyGuest) booking.getGuestId();
                bookingInfo.setId(booking.getId());
                bookingInfo.setName(bGuest.getCompanyName());
                bookingInfo.setEmailAddress(bGuest.getEmailAddress());
                bookingInfo.setCountry(bGuest.getCountry());
                bookingInfo.setNumOfRes(bookServ.returnNumOfReservations(booking.getId()));
            }
            infoList.add(bookingInfo);
        }
        if (!orderBy.equals("")) {
            infoList = searchParamSelector(orderBy, infoList);
        }
        model.addAttribute("infoList", infoList);
        return "bookingList";
    }

    public List<BookingInfo> searchParamSelector(String orderBy, List<BookingInfo> infoList) {
        if (orderBy.equals("name")) {
            Collections.sort(infoList, new NameComparator());
            if (searchParamCheck[0] == 1) {
                Collections.reverse(infoList);
                Arrays.fill(searchParamCheck, 0);
            } else {
                searchParamCheck[0] = 1;
            }
        };
        if (orderBy.equals("emailAddress")) {
            Collections.sort(infoList, new EmailComparator());
            if (searchParamCheck[1] == 1) {
                Collections.reverse(infoList);
                Arrays.fill(searchParamCheck, 0);
            } else {
                searchParamCheck[1] = 1;
            }
        };
        if (orderBy.equals("country")) {
            Collections.sort(infoList, new CountryComparator());
            if (searchParamCheck[2] == 1) {
                Collections.reverse(infoList);
                Arrays.fill(searchParamCheck, 0);
            } else {
                searchParamCheck[2] = 1;
            }
        };
        if (orderBy.equals("resNumber")) {
            Collections.sort(infoList, new ResNumberComparator());
            if (searchParamCheck[3] == 1) {
                Collections.reverse(infoList);
                Arrays.fill(searchParamCheck, 0);
            } else {
                searchParamCheck[3] = 1;
            }
        };
        return infoList;
    }

}
