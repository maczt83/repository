package progmatic.bookingmanager.controllers;

import java.io.IOException;
import java.text.ParseException;
import javax.validation.Valid;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import progmatic.bookingmanager.dtos.PaymentLinkDetailRequestToBackendDto;
import progmatic.bookingmanager.dtos.PaymentLinkDetailsDto;
import progmatic.bookingmanager.dtos.PaymentLinkRequestToBackendDto;
import progmatic.bookingmanager.services.PaymentGatewayService;

@Controller
public class PaymentController {

    @Autowired
    PaymentGatewayService paymentGatewayService;
    
    @RequestMapping(value = {"booking/{bookingId}/payment"}, method = RequestMethod.GET)
    public String addPaymentDetails (@PathVariable("bookingId")String bookingId, Model model) {
        PaymentLinkRequestToBackendDto paymentLinkRequestToBackendDto = new PaymentLinkRequestToBackendDto();
        paymentLinkRequestToBackendDto.setBookingId(bookingId);
        model.addAttribute("paymentData", paymentLinkRequestToBackendDto );
        return "payment_details";
    }
    
    @RequestMapping(value = {"/paymentlink"}, method = RequestMethod.GET)
    public String addPaymentLinkDetails () {
        return "paymentlink_details";
    }
    
    @RequestMapping(value = {"booking/{bookingId}/payment/response"}, method = RequestMethod.GET)
    public String showPaymentLink (@PathVariable("bookingId")Long bookingId, Model model) {
        String paymentLinkFromDBByBookingId = paymentGatewayService.getPaymentLinkFromDBByBookingId(bookingId);
        model.addAttribute("bookingId", bookingId);
        model.addAttribute("returnString", paymentLinkFromDBByBookingId);
        return "response";
    }
    
    @RequestMapping(path = "/payment", method = RequestMethod.POST)
    public String createDetailsForPaymentLink(@Valid PaymentLinkRequestToBackendDto plrbdto, BindingResult bindingResult, Model model) throws IOException, ParseException {
        if (bindingResult.hasErrors()) model.addAttribute("guestData",plrbdto);
        paymentGatewayService.giveTheAnswerToAppAndDoDBChanges(plrbdto);
        return "redirect:/booking/"+plrbdto.getBookingId()+"/payment/response";
    }
    
    @RequestMapping(path = "/rest/paymentDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createGetPaymentLinkDetails(PaymentLinkDetailRequestToBackendDto paymentLinkName) throws IOException, ParseException {
        HttpResponse response = paymentGatewayService.getPaymentLinkDetails(paymentLinkName);
        PaymentLinkDetailsDto linkDetails = paymentGatewayService.processResponseBodyForPaymentLinkDetails(response);
        paymentGatewayService.addTransactionDataToDB(linkDetails);
        return "redirect:/paymentLink/" + paymentLinkName.getPaymentLinkName() ;
    }
    
}
