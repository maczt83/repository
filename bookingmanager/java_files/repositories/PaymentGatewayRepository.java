/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.PaymentGateway;

/**
 *
 * @author chris
 */
public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, Long>{
    PaymentGateway findById(long id);
    List <PaymentGateway> findByBookingId(Booking bookingId);
    PaymentGateway findByPaymentLinkName(String linkname);
    List<PaymentGateway> findByPaymentLinkStatus(String status);
    List<PaymentGateway> findByCreationDate(Date date);
    List<PaymentGateway> findByLastUpdate(Date date);
    List<PaymentGateway> findByBookingIdOrderByCreationDateDesc(Booking booking);
    
}
