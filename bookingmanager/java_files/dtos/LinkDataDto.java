/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.dtos;

import java.util.Date;

/**
 *
 * @author User
 */
public class LinkDataDto {
    private Date paymentCreationDate;
    private String paymentStatus;
    private String paymentLink;

    public Date getPaymentCreationDate() {
        return paymentCreationDate;
    }

    public void setPaymentCreationDate(Date paymentCreationDate) {
        this.paymentCreationDate = paymentCreationDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }
    
    
}
