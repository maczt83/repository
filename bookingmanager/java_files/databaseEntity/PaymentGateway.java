/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import progmatic.bookingmanager.utils.DateConstants;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "paymentGateway")
public class PaymentGateway implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Booking bookingId;
    
    private String paymentLinkName;
    
    private String paymentLink;
    
    private String paymentLinkStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = DateConstants.DATE_TIMEWITHSEC_FORMAT)
    private Date creationDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = DateConstants.DATE_TIMEWITHSEC_FORMAT)
    private Date lastUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentGatewayId")
    private List<PaymentTransaction> paymentTransactionList;
    
    private BigDecimal amount;
    
    @Size(max=3)
    private String currency;
    
    private String requestData;
    @Lob
    private String responseData;

    public PaymentGateway() {
        
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public String getPaymentLinkName() {
        return paymentLinkName;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public String getPaymentLinkStatus() {
        return paymentLinkStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public List<PaymentTransaction> getPaymentTransactionList() {
        return paymentTransactionList;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void addTransactionToList(PaymentTransaction paymentTransaction){
        paymentTransactionList.add(paymentTransaction);
    }

    public String getRequestData() {
        return requestData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public void setPaymentLinkName(String paymentLinkName) {
        this.paymentLinkName = paymentLinkName;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public void setPaymentLinkStatus(String paymentLinkStatus) {
        this.paymentLinkStatus = paymentLinkStatus;
    }

    public void setCreationDate(String creationDate) throws ParseException {
        DateFormat format = new SimpleDateFormat(DateConstants.DATE_TIMEWITHSEC_FORMAT);
        this.creationDate = format.parse(creationDate);
    }

    public void setLastUpdate(String lastUpdate) throws ParseException {
        DateFormat format = new SimpleDateFormat(DateConstants.DATE_TIMEWITHSEC_FORMAT);
        this.lastUpdate = format.parse(lastUpdate);
    }

    public void setPaymentTransactionList(List<PaymentTransaction> paymentTransactionList) {
        this.paymentTransactionList = paymentTransactionList;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    
    
}
