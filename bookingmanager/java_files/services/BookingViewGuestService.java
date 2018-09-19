/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;
import progmatic.bookingmanager.databaseEntity.Guest;
import progmatic.bookingmanager.databaseEntity.PaymentGateway;
import progmatic.bookingmanager.databaseEntity.PersonGuest;
import progmatic.bookingmanager.dtos.GuestBookingViewDto;
import progmatic.bookingmanager.dtos.LinkDataDto;
import progmatic.bookingmanager.enums.GuestTitleEnum;
import progmatic.bookingmanager.repositories.GuestRepository;
import progmatic.bookingmanager.repositories.PaymentGatewayRepository;

/**
 *
 * @author User
 */
@Service
public class BookingViewGuestService {
   @PersistenceContext
    EntityManager em;

    @Autowired
    GuestRepository guestRep;
    
    @Autowired
    PaymentGatewayRepository pGRep;
    
    public GuestBookingViewDto getBookingViewDto (Booking booking){
        Guest guest = guestRep.findById(booking.getGuestId().getId());
        List <PaymentGateway> paymentGateways = pGRep.findByBookingId(booking);
        GuestBookingViewDto guestDto = new GuestBookingViewDto();
        if(guest.getTitle().equals("COMPANY")){
            CompanyGuest cGuest = (CompanyGuest)guest;
            guestDto.setGuestName(cGuest.getCompanyName());
        }else{
            PersonGuest pGuest = (PersonGuest)guest;
            guestDto.setGuestName(pGuest.getFirstName()+" "+pGuest.getLastName());
        }
        guestDto.setAddressLine1(guest.getCity()+" "+guest.getCountry());
        guestDto.setAddressLine2(guest.getZip()+" "+guest.getAddress1());
        guestDto.setEmail(guest.getEmailAddress());
        guestDto.setPhoneNumber(guest.getPhoneNumber());
        guestDto.setVatid(guest.getVatID());
        guestDto.setTaxNo(guest.getTaxNo());
        guestDto.setZip(guest.getZip());
        if(paymentGateways!=null){
            for (PaymentGateway paymentGateway : paymentGateways) {
                LinkDataDto linkData = new LinkDataDto();
                linkData.setPaymentCreationDate(paymentGateway.getCreationDate());
                linkData.setPaymentLink(paymentGateway.getPaymentLinkName());
                linkData.setPaymentStatus(paymentGateway.getPaymentLinkStatus());
                guestDto.addLinkData(linkData);
            }    
        }
        return guestDto;
    }
}
