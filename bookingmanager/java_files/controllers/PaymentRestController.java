package progmatic.bookingmanager.controllers;

import java.io.IOException;
import java.text.ParseException;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import progmatic.bookingmanager.services.PaymentGatewayService;
import org.springframework.web.bind.annotation.RestController;
import progmatic.bookingmanager.dtos.PaymentLinkDetailRequestToBackendDto;
import progmatic.bookingmanager.dtos.PaymentLinkDetailsDto;
import progmatic.bookingmanager.dtos.PaymentLinkRequestToBackendDto;

@RestController
@RequestMapping("rest")
public class PaymentRestController {

    @Autowired
    PaymentGatewayService paymentGatewayService;


//    @RequestMapping(path = "/rest/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String createDetailsForPaymentLink(PaymentLinkRequestToBackendDto plrbdto) throws IOException, ParseException {
//        return paymentGatewayService.giveTheAnswerToAppAndDoDBChanges(plrbdto);
//    }

//    @RequestMapping(path = "paymentDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String createGetPaymentLinkDetails(PaymentLinkDetailRequestToBackendDto paymentLinkName) throws IOException, ParseException {
//        HttpResponse response = paymentGatewayService.getPaymentLinkDetails(paymentLinkName);
//        PaymentLinkDetailsDto linkDetails = paymentGatewayService.processResponseBodyForPaymentLinkDetails(response);
//        paymentGatewayService.addTransactionDataToDB(linkDetails);
//        return hm;
//    }


}
