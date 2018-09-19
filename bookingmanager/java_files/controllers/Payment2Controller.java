/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import progmatic.bookingmanager.repositories.PaymentGatewayRepository;
import progmatic.bookingmanager.repositories.PaymentTransactionRepository;
import progmatic.bookingmanager.services.PaymentGatewayService;

/**
 *
 * @author Stankye
 */
@Controller
public class Payment2Controller {
    
    @Autowired
    PaymentGatewayRepository paymentGatewayRepository;
    
    @Autowired
    PaymentTransactionRepository paymentTransactionRepository;
    
    @RequestMapping(value ="/paymentLink/{paymentLinkName}")
    public String viewSpecificPayment(Model model, @PathVariable("paymentLinkName")String paymentLinkName){
        model.addAttribute("payment", paymentGatewayRepository.findByPaymentLinkName(paymentLinkName));
        return "paymentlink_name_details";
    }
    
    @RequestMapping(value ="/paymentLink/{paymentLinkName}/{transactionId}")
    public String viewSpecificTransaction(Model model, 
            @PathVariable("paymentLinkName")String paymentLinkName, 
            @PathVariable("transactionId")String transactionId){
        model.addAttribute("transaction", paymentTransactionRepository.findByTransactionId(transactionId));
        return "paymentlink_name_details2";
    }
}
