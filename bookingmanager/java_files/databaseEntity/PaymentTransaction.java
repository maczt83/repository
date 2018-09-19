/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "paymentTransaction")
public class PaymentTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "paymentGateway", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PaymentGateway paymentGatewayId;
    @NotNull
    private String transactionId;
    @NotNull
    private String providerTransactionId;
    private String anum;
    @NotNull
    private String resultCode;
    private String resultMessage;
    private String commitState;
    private String autoCommit;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationDate;

    public PaymentTransaction(long id, PaymentGateway paymentGatewayId, String transactionId, String providerTransactionId, String anum, String resultCode, String resultMessage, String commitState, String autoCommit, Calendar creationDate) {
        this.id = id;
        this.paymentGatewayId = paymentGatewayId;
        this.transactionId = transactionId;
        this.providerTransactionId = providerTransactionId;
        this.anum = anum;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.commitState = commitState;
        this.autoCommit = autoCommit;
        this.creationDate = creationDate;
    }
    
 

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public PaymentTransaction() {
        
    }

    public long getId() {
        return id;
    }

    public PaymentGateway getPaymentGatewayId() {
        return paymentGatewayId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getProviderTransactionId() {
        return providerTransactionId;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPaymentGatewayId(PaymentGateway paymentGatewayId) {
        this.paymentGatewayId = paymentGatewayId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    

    public void setProviderTransactionId(String providerTransactionId) {
        this.providerTransactionId = providerTransactionId;
    }

    public void setAnum(String anum) {
        this.anum = anum;
    }

    public void setCommitState(String commitState) {
        this.commitState = commitState;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getAnum() {
        return anum;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public String getCommitState() {
        return commitState;
    }

    public String getAutoCommit() {
        return autoCommit;
    }
    
}
