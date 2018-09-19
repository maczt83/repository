/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import progmatic.bookingmanager.databaseEntity.Amenity;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;
import progmatic.bookingmanager.databaseEntity.EmailTemplate;
import progmatic.bookingmanager.databaseEntity.Guest;
import progmatic.bookingmanager.databaseEntity.PersonGuest;
import progmatic.bookingmanager.databaseEntity.Reservation;
import progmatic.bookingmanager.databaseEntity.Role;
import progmatic.bookingmanager.databaseEntity.Room;
import progmatic.bookingmanager.databaseEntity.RoomRate;
import progmatic.bookingmanager.databaseEntity.RoomType;
import progmatic.bookingmanager.databaseEntity.User;
import progmatic.bookingmanager.repositories.AmenityRepository;
import progmatic.bookingmanager.repositories.BookingRepository;
import progmatic.bookingmanager.repositories.CompanyGuestRepository;
import progmatic.bookingmanager.repositories.EmailTemplateRepository;
import progmatic.bookingmanager.repositories.GuestRepository;
import progmatic.bookingmanager.repositories.PersonGuestRepository;
import progmatic.bookingmanager.repositories.ReservationRepository;
import progmatic.bookingmanager.repositories.RoleRepository;
import progmatic.bookingmanager.repositories.RoomRateRepository;
import progmatic.bookingmanager.repositories.RoomRepository;
import progmatic.bookingmanager.repositories.RoomTypeRepository;
import progmatic.bookingmanager.repositories.UserRepository;

/**
 *
 * @author Stankye
 */
@Component
public class LoadDefaultData {

    @Autowired
    ReservationRepository reservationRepository;
    
    @Autowired
    BookingRepository bookingRepository;
    
    @Autowired
    GuestRepository guestRepository;
    
    @Autowired
    CompanyGuestRepository companyGuestRepository;
    
    @Autowired
    PersonGuestRepository personGuestRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    RoomRateRepository roomRateRepository;

    @Autowired
    AmenityRepository amenityRepository;
    
    @Autowired 
    EmailTemplateRepository emailTemplateRepository;
    
    @Autowired
    PasswordEncoder encoder;


    @PostConstruct
    public void init(){
        fillDatabaseWithDeafaultRoles();
        fillDatabaseWithDefaultUser();
        fillDatabaseWithDefaultReceptionist();
        setDefaultRoomTypeDummies();
        setDeafultRoomRateDummies();
        setDefaultAmenityDummies();
        setDefaultRoomDummies();
        setDefaultGuestDummies();
        setDefaultBookingDummies();
        setDefaultReservationDummies();
        setDefaultEmailTemplateDummies();
    }
    public void fillDatabaseWithDeafaultRoles() {
        if (roleRepository.findByRoleName("manager") == null) {
            Role role = new Role("manager");
            roleRepository.save(role);
        }
        if (roleRepository.findByRoleName("receptionist") == null) {
            Role role1 = new Role("receptionist");
            roleRepository.save(role1);
        }
    }

    public void fillDatabaseWithDefaultUser() {
        if (userRepository.findByUsername("admin") == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(encoder.encode("init1234"));
            user.setEmail("bookingproj123@gmail.com");
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setRole(roleRepository.findById(1));
            user.setActive((short) 1);
            userRepository.save(user);
        }
    }

   
    public void fillDatabaseWithDefaultReceptionist() {
        if (userRepository.findByUsername("receptionist") == null) {
            User user = new User();
            user.setUsername("receptionist");
            user.setPassword(encoder.encode("init1234"));
            user.setEmail("receptionist@admin.com");
            user.setFirstName("receptionist");
            user.setLastName("receptionist");
            user.setRole(roleRepository.findById(2));
            user.setActive((short) 1);
            userRepository.save(user);
        }
    }

    
    public void setDefaultRoomTypeDummies() {
        if (roomTypeRepository.findByName("twoBedSingle") == null) {
            RoomType twoBedSingle = new RoomType();
            twoBedSingle.setName("twoBedSingle");
            roomTypeRepository.save(twoBedSingle);
        }
        if (roomTypeRepository.findByName("twoBedDouble") == null) {
            RoomType twoBedDouble = new RoomType();
            twoBedDouble.setName("twoBedDouble");
            roomTypeRepository.save(twoBedDouble);
        }
        if (roomTypeRepository.findByName("threeBedSingle") == null) {
            RoomType threeBedSingle = new RoomType();
            threeBedSingle.setName("threeBedSingle");
            roomTypeRepository.save(threeBedSingle);
        }

    }

    
    public void setDeafultRoomRateDummies() {
        if (roomRateRepository.findById(1) == null) {
            RoomRate winterSeason1 = new RoomRate();
            winterSeason1.setRate(15000);
            winterSeason1.setStartDate(new Date(118, 0, 4));
            winterSeason1.setEndDate(new Date(118, 2, 1));
            winterSeason1.setRoomTypeid(roomTypeRepository.findById((long) 1));
            roomRateRepository.save(winterSeason1);
        }
        if (roomRateRepository.findById(2) == null) {
            RoomRate winterSeason2 = new RoomRate();
            winterSeason2.setRate(15000);
            winterSeason2.setStartDate(new Date(118, 0, 4));
            winterSeason2.setEndDate(new Date(118, 2, 1));
            winterSeason2.setRoomTypeid(roomTypeRepository.findById((long) 2));
            roomRateRepository.save(winterSeason2);
        }
        if (roomRateRepository.findById(3) == null) {
            RoomRate winterSeason3 = new RoomRate();
            winterSeason3.setRate(15000);
            winterSeason3.setStartDate(new Date(118, 0, 4));
            winterSeason3.setEndDate(new Date(118, 2, 1));
            winterSeason3.setRoomTypeid(roomTypeRepository.findById((long) 3));
            roomRateRepository.save(winterSeason3);
        }
        if (roomRateRepository.findById(4) == null) {
            RoomRate springSeason1 = new RoomRate();
            springSeason1.setRate(13000);
            springSeason1.setStartDate(new Date(118, 2, 2));
            springSeason1.setEndDate(new Date(118, 4, 31));
            springSeason1.setRoomTypeid(roomTypeRepository.findById((long) 1));
            roomRateRepository.save(springSeason1);
        }
        if (roomRateRepository.findById(5) == null) {
            RoomRate springSeason2 = new RoomRate();
            springSeason2.setRate(13000);
            springSeason2.setStartDate(new Date(118, 2, 2));
            springSeason2.setEndDate(new Date(118, 4, 31));
            springSeason2.setRoomTypeid(roomTypeRepository.findById((long) 2));
            roomRateRepository.save(springSeason2);
        }
        if (roomRateRepository.findById(6) == null) {
            RoomRate springSeason3 = new RoomRate();
            springSeason3.setRate(13000);
            springSeason3.setStartDate(new Date(118, 2, 2));
            springSeason3.setEndDate(new Date(118, 4, 31));
            springSeason3.setRoomTypeid(roomTypeRepository.findById((long) 3));
            roomRateRepository.save(springSeason3);
        }
        if (roomRateRepository.findById(7) == null) {
            RoomRate summerSeason1 = new RoomRate();
            summerSeason1.setRate(20000);
            summerSeason1.setStartDate(new Date(118, 5, 1));
            summerSeason1.setEndDate(new Date(118, 7, 20));
            summerSeason1.setRoomTypeid(roomTypeRepository.findById((long) 1));
            roomRateRepository.save(summerSeason1);
        }
        if (roomRateRepository.findById(8) == null) {
            RoomRate summerSeason2 = new RoomRate();
            summerSeason2.setRate(20000);
            summerSeason2.setStartDate(new Date(118, 5, 1));
            summerSeason2.setEndDate(new Date(118, 7, 20));
            summerSeason2.setRoomTypeid(roomTypeRepository.findById((long) 2));
            roomRateRepository.save(summerSeason2);
        }
        if (roomRateRepository.findById(9) == null) {
            RoomRate summerSeason3 = new RoomRate();
            summerSeason3.setRate(20000);
            summerSeason3.setStartDate(new Date(118, 5, 1));
            summerSeason3.setEndDate(new Date(118, 7, 20));
            summerSeason3.setRoomTypeid(roomTypeRepository.findById((long) 3));
            roomRateRepository.save(summerSeason3);
        }
        if (roomRateRepository.findById(10) == null) {
            RoomRate fallSeason1 = new RoomRate();
            fallSeason1.setRate(18000);
            fallSeason1.setStartDate(new Date(118, 7, 21));
            fallSeason1.setEndDate(new Date(118, 8, 31));
            fallSeason1.setRoomTypeid(roomTypeRepository.findById((long) 1));
            roomRateRepository.save(fallSeason1);
        }
        if (roomRateRepository.findById(11) == null) {
            RoomRate fallSeason2 = new RoomRate();
            fallSeason2.setRate(18000);
            fallSeason2.setStartDate(new Date(118, 7, 21));
            fallSeason2.setEndDate(new Date(118, 8, 31));
            fallSeason2.setRoomTypeid(roomTypeRepository.findById((long) 2));
            roomRateRepository.save(fallSeason2);
        }
        if (roomRateRepository.findById(12) == null) {
            RoomRate fallSeason3 = new RoomRate();
            fallSeason3.setRate(18000);
            fallSeason3.setStartDate(new Date(118, 7, 21));
            fallSeason3.setEndDate(new Date(118, 8, 31));
            fallSeason3.setRoomTypeid(roomTypeRepository.findById((long) 3));
            roomRateRepository.save(fallSeason3);
        }
        if (roomRateRepository.findById(13) == null) {
            RoomRate fallSeason4 = new RoomRate();
            fallSeason4.setRate(13000);
            fallSeason4.setStartDate(new Date(118, 9, 1));
            fallSeason4.setEndDate(new Date(118, 11, 20));
            fallSeason4.setRoomTypeid(roomTypeRepository.findById((long) 1));
            roomRateRepository.save(fallSeason4);
        }
        if (roomRateRepository.findById(14) == null) {
            RoomRate fallSeason5 = new RoomRate();
            fallSeason5.setRate(13000);
            fallSeason5.setStartDate(new Date(118, 9, 1));
            fallSeason5.setEndDate(new Date(118, 11, 20));
            fallSeason5.setRoomTypeid(roomTypeRepository.findById((long) 2));
            roomRateRepository.save(fallSeason5);
        }
        if (roomRateRepository.findById(15) == null) {
            RoomRate fallSeason6 = new RoomRate();
            fallSeason6.setRate(13000);
            fallSeason6.setStartDate(new Date(118, 9, 1));
            fallSeason6.setEndDate(new Date(118, 11, 20));
            fallSeason6.setRoomTypeid(roomTypeRepository.findById((long) 3));
            roomRateRepository.save(fallSeason6);
        }
        if (roomRateRepository.findById(16) == null) {
            RoomRate winterHolidays1 = new RoomRate();
            winterHolidays1.setRate(20000);
            winterHolidays1.setStartDate(new Date(118, 11, 21));
            winterHolidays1.setEndDate(new Date(119, 0, 3));
            winterHolidays1.setRoomTypeid(roomTypeRepository.findById((long) 1));
            roomRateRepository.save(winterHolidays1);
        }
        if (roomRateRepository.findById(17) == null) {
            RoomRate winterHolidays2 = new RoomRate();
            winterHolidays2.setRate(20000);
            winterHolidays2.setStartDate(new Date(118, 11, 21));
            winterHolidays2.setEndDate(new Date(119, 0, 3));
            winterHolidays2.setRoomTypeid(roomTypeRepository.findById((long) 2));
            roomRateRepository.save(winterHolidays2);
        }
        if (roomRateRepository.findById(18) == null) {
            RoomRate winterHolidays3 = new RoomRate();
            winterHolidays3.setRate(20000);
            winterHolidays3.setStartDate(new Date(118, 11, 21));
            winterHolidays3.setEndDate(new Date(119, 0, 3));
            winterHolidays3.setRoomTypeid(roomTypeRepository.findById((long) 3));
            roomRateRepository.save(winterHolidays3);
        }

    }

    
    public void setDefaultAmenityDummies() {
        String[] amenities = {"TV", "fridge", "boiler", "hairdryer"};
        for (String amenityName : amenities) {
            if (amenityRepository.findByName(amenityName) == null) {
                Amenity amenity = new Amenity();
                amenity.setName(amenityName);
                amenityRepository.save(amenity);
            }
        }
    }

    public void setDefaultRoomDummies() {
        if (roomRepository.findById(1) == null) {
            Room room = new Room();
            room.setCapacity((short) 2);
            room.setExtraBed((short) 1);
            room.setBuilding((short) 1);
            room.setFloor((short) 1);
            room.setRoomNumber(1);
            room.setAmenityList(amenityRepository.findAll());
            room.setRoomType(roomTypeRepository.findById((long) 1));
            roomRepository.save(room);
        }
        if (roomRepository.findById(2) == null) {
            Room room = new Room();
            room.setCapacity((short) 2);
            room.setExtraBed((short) 1);
            room.setBuilding((short) 1);
            room.setFloor((short) 2);
            room.setRoomNumber(11);
            room.setAmenityList(amenityRepository.findAll());
            room.setRoomType(roomTypeRepository.findById((long) 2));
            roomRepository.save(room);
        }
        if (roomRepository.findById(3) == null) {
            Room room = new Room();
            room.setCapacity((short) 3);
            room.setExtraBed((short) 1);
            room.setBuilding((short) 1);
            room.setFloor((short) 1);
            room.setRoomNumber(111);
            room.setAmenityList(amenityRepository.findAll());
            room.setRoomType(roomTypeRepository.findById((long) 3));
            roomRepository.save(room);
        }
        if (roomRepository.findById(1) == null) {
            Room room = new Room();
            room.setCapacity((short) 2);
            room.setExtraBed((short) 1);
            room.setBuilding((short) 4);
            room.setFloor((short) 1);
            room.setRoomNumber(41);
            room.setAmenityList(amenityRepository.findAll());
            room.setRoomType(roomTypeRepository.findById((long) 1));
            roomRepository.save(room);
        }
        if (roomRepository.findById(2) == null) {
            Room room = new Room();
            room.setCapacity((short) 2);
            room.setExtraBed((short) 1);
            room.setBuilding((short) 3);
            room.setFloor((short) 2);
            room.setRoomNumber(311);
            room.setAmenityList(amenityRepository.findAll());
            room.setRoomType(roomTypeRepository.findById((long) 2));
            roomRepository.save(room);
        }
        if (roomRepository.findById(3) == null) {
            Room room = new Room();
            room.setCapacity((short) 3);
            room.setExtraBed((short) 1);
            room.setBuilding((short) 2);
            room.setFloor((short) 1);
            room.setRoomNumber(21);
            room.setAmenityList(amenityRepository.findAll());
            room.setRoomType(roomTypeRepository.findById((long) 3));
            roomRepository.save(room);
        }
    }
    
    
    public void setDefaultGuestDummies(){
        if(guestRepository.findById((long) 1) == null){
            CompanyGuest guest = new CompanyGuest();
            guest.setEmailAddress("kovacsbt@gmail.com");
            guest.setPhoneNumber("06301234567");
            guest.setTitle("COMPANY");
            guest.setCountry("Hungary");
            guest.setAddress1("Csücsök utca 2");
            guest.setCity("Budapest");
            guest.setZip("2345");
            guest.setCompanyName("Kovács Bt Hungary");
            guest.setTaxNo("5545254-2-13");
            companyGuestRepository.save(guest);
        }
        if(guestRepository.findById((long) 2) == null){
            PersonGuest guest = new PersonGuest();
            guest.setFirstName("Béla");
            guest.setLastName("Kovács");
            guest.setEmailAddress("kovacsbela@gmail.com");
            guest.setPhoneNumber("06301234567");
            guest.setTitle("MALE");
            guest.setCountry("United Kingdom");
            guest.setAddress1("Csücsök str. 2");
            guest.setCity("London");
            guest.setZip("234 665 NW");
            guest.setVatID("455435459");
            personGuestRepository.save(guest);
        }
        if(guestRepository.findById((long) 3) == null){
            PersonGuest guest = new PersonGuest();
            guest.setFirstName("Béla");
            guest.setLastName("Kovács");
            guest.setEmailAddress("kovacsbl@gmail.com");
            guest.setPhoneNumber("06301234567");
            guest.setTitle("MALE");
            guest.setCountry("Hungary");
            guest.setAddress1("Csücsök utca 2");
            guest.setCity("Budapest");
            guest.setZip("2345");
            guest.setTaxNo("4342323213");
            personGuestRepository.save(guest);
        }
        if(guestRepository.findById((long) 4) == null){
            PersonGuest guest = new PersonGuest();
            guest.setFirstName("Irén");
            guest.setLastName("Kovács");
            guest.setEmailAddress("kovacsiren@gmail.com");
            guest.setPhoneNumber("06301234567");
            guest.setTitle("FEMALE");
            guest.setCountry("Hungary");
            guest.setAddress1("Csücsök utca 2");
            guest.setCity("Budapest");
            guest.setZip("2345");
            guest.setTaxNo("2121212121");
            personGuestRepository.save(guest);
        }
        if(guestRepository.findById((long) 5) == null){
            PersonGuest guest = new PersonGuest();
            guest.setFirstName("Sándor");
            guest.setLastName("Nándi");
            guest.setEmailAddress("nandisanyi@gmail.com");
            guest.setPhoneNumber("06301234567");
            guest.setTitle("MALE");
            guest.setCountry("Germany");
            guest.setAddress1("Csücsök strasse 5");
            guest.setCity("Berlin");
            guest.setZip("G-44321");
            guest.setVatID("3535245");
            personGuestRepository.save(guest);
        }
        if(guestRepository.findById((long) 6) == null){
            CompanyGuest guest = new CompanyGuest();
            guest.setEmailAddress("kovibt@gmail.com");
            guest.setPhoneNumber("06301234567");
            guest.setTitle("COMPANY");
            guest.setCountry("Hungary");
            guest.setAddress1("Csücsök utca 2");
            guest.setCity("Budapest");
            guest.setZip("2345");
            guest.setCompanyName("Kovi Bt Hungary");
            guest.setTaxNo("5545254-2-13");
            companyGuestRepository.save(guest);
        }
        if(guestRepository.findById((long) 6) == null){
            PersonGuest guest = new PersonGuest();
            guest.setFirstName("Valaki");
            guest.setLastName("Próba");
            guest.setEmailAddress("valaki@gmail.com");
            guest.setPhoneNumber("06301234567");
            guest.setTitle("MALE");
            guest.setCountry("Hungary");
            guest.setAddress1("Petőfi utca 5.");
            guest.setCity("Szeged");
            guest.setZip("2345");
            personGuestRepository.save(guest);
        }
    }
    
    
    public void setDefaultBookingDummies(){
        if(bookingRepository.findById(1) == null){
            Booking booking = new Booking();
            booking.setGuestId(guestRepository.findById((long) 1));
            bookingRepository.save(booking);
        }
        if(bookingRepository.findById(2) == null){
            Booking booking = new Booking();
            booking.setGuestId(guestRepository.findById((long) 2));
            bookingRepository.save(booking);
        }
        if(bookingRepository.findById(3) == null){
            Booking booking = new Booking();
            booking.setGuestId(guestRepository.findById((long) 3));
            bookingRepository.save(booking);
        }
        if(bookingRepository.findById(4) == null){
            Booking booking = new Booking();
            booking.setGuestId(guestRepository.findById((long) 4));
            bookingRepository.save(booking);
        }
        if(bookingRepository.findById(5) == null){
            Booking booking = new Booking();
            booking.setGuestId(guestRepository.findById((long) 5));
            bookingRepository.save(booking);
        }
         if(bookingRepository.findById(6) == null){
            Booking booking = new Booking();
            booking.setGuestId(guestRepository.findById((long) 6));
            bookingRepository.save(booking);
        }
    }
    
    
    public void setDefaultReservationDummies(){
        if(reservationRepository.findById((long) 1) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(1));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(1));
            res.setStartDate(new Date(118, 5, 15));
            res.setEndDate(new Date(118, 5, 22));
            res.setCheckInTime(new Date(118, 5, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 2) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(1));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(2));
            res.setStartDate(new Date(118, 5, 15));
            res.setEndDate(new Date(118, 5, 22));
            res.setCheckInTime(new Date(118, 5, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 9) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(6));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(3));
            res.setStartDate(new Date(118, 5, 15));
            res.setEndDate(new Date(118, 5, 22));
            res.setCheckInTime(new Date(118, 5, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 3) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(2));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(3));
            res.setStartDate(new Date(118, 6, 15));
            res.setEndDate(new Date(118, 6, 22));
            res.setCheckInTime(new Date(118, 6, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 4) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(3));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(1));
            res.setStartDate(new Date(118, 4, 15));
            res.setEndDate(new Date(118, 4, 22));
            res.setCheckInTime(new Date(118, 4, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 5) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(3));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(2));
            res.setStartDate(new Date(118, 4, 15));
            res.setEndDate(new Date(118, 4, 22));
            res.setCheckInTime(new Date(118, 4, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 6) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(3));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(3));
            res.setStartDate(new Date(118, 4, 15));
            res.setEndDate(new Date(118, 4, 22));
            res.setCheckInTime(new Date(118, 4, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 7) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(4));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(1));
            res.setStartDate(new Date(118, 2, 15));
            res.setEndDate(new Date(118, 2, 22));
            res.setCheckInTime(new Date(118, 2, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
        if(reservationRepository.findById((long) 8) == null){
            Reservation res = new Reservation();
            res.setBookingId(bookingRepository.findById(5));
            res.setBreakfast((short) 1);
            res.setRoomId(roomRepository.findById(1));
            res.setStartDate(new Date(118, 1, 15));
            res.setEndDate(new Date(118, 1, 22));
            res.setCheckInTime(new Date(118, 1, 15));
            res.setPaid((short)0);
            reservationRepository.save(res);
        }
    }
    
    public void setDefaultEmailTemplateDummies(){
        if(emailTemplateRepository.findById(1) == null){
            EmailTemplate emailTemplate = new EmailTemplate();
            emailTemplate.setSubject("Password reset request");
            emailTemplate.setHtml("<h1>Hi %nameOfUser%!</h1>\n" +
                "<p>You've recently requested to reset your password for your BookingManager account.</p>\n" +
                "<p>Click on this link to reset it: <a href=\"http://localhost:8080/password_reset?token=%passwordResetToken%\" > Reset My Password</a><p>\n" +
                "<p style=\"font-size:13px;\">If you have not request a password reset, please ignore this email or reply to let us know.<br>Thanks, The BookingManager Team</p>");
            emailTemplateRepository.save(emailTemplate);
        }
        if(emailTemplateRepository.findById(2) == null){
            EmailTemplate emailTemplate = new EmailTemplate();
            emailTemplate.setSubject("Your payment link");
            emailTemplate.setHtml("<h2>Dear %nameOfUser%,</h2>\n" +
                "<p>You hereby receive this email to pay for your booking.<br>\n" +
                "Amount: %amount%\n" +
                "<br>Please click on the following link to start the payment process: <a href=\"%paymentLink%\" > Start Payment</a><p>\n" +
                "<p>Kind regards,<br>The BookingManager Team</p>");
            emailTemplateRepository.save(emailTemplate);
        }
        if(emailTemplateRepository.findById(3) == null){
            EmailTemplate emailTemplate = new EmailTemplate();
            emailTemplate.setSubject("Dummy Template2");
            emailTemplate.setHtml("<h1>Asdasdasd!</h1>\n" +
                "<p>You've recently requested to reset your password for your BookingManager account.</p>\n" +
                "<p>Click on this link to reset it: <a href=\"http://localhost:8080/password_reset?token=%passwordResetToken%\" > Reset My Password</a><p>\n" +
                "<p style=\"font-size:13px;\">If you have not request a password reset, please ignore this email or reply to let us know.<br>Thanks, The BookingManager Team</p>");
            emailTemplateRepository.save(emailTemplate);
        }
    }

}
