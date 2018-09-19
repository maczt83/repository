/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import progmatic.bookingmanager.services.PaymentGatewaySearchService;

/**
 *
 * @author Anna
 */
@Controller
public class PaymentGatewaySearchController {

    @Autowired
    PaymentGatewaySearchService pgSearchService;

    @RequestMapping(path = "/payment/search", method = RequestMethod.GET)
    public String searchRooms(
            @RequestParam(name = "bookingId", required = false) Long bookingId,
            @RequestParam(name = "linkName", required = false) String linkName,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "creationDate", required = false) String creationDate,
            @RequestParam(name = "lastUpdate", required = false) String lastUpdate,
            Model model) {

        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date creationDateD = new GregorianCalendar(0000, Calendar.JANUARY, 01).getTime();
        Date lastUpdateD = new GregorianCalendar(0000, Calendar.JANUARY, 01).getTime();
        if (creationDate != "") {
            if (creationDate != null) {
                try {
                    creationDateD = formatter.parse(creationDate);
                } catch (ParseException ex) {
                    Logger.getLogger(PaymentGatewaySearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (lastUpdate != "") {
            if (lastUpdate != null) {
                try {
                    lastUpdateD = formatter.parse(lastUpdate);
                } catch (ParseException ex) {
                    Logger.getLogger(PaymentGatewaySearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        model.addAttribute("paymentList", pgSearchService.searchPaymentGatewayByParameters(bookingId, linkName, status, creationDateD, lastUpdateD));
        return "paymentGateway-search";

    }

}
