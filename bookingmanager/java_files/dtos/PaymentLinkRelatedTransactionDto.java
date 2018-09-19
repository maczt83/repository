
package progmatic.bookingmanager.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentLinkRelatedTransactionDto implements Serializable
{

    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("resultMessage")
    @Expose
    private String resultMessage;
    @SerializedName("anum")
    @Expose
    private String anum;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("providerTransactionId")
    @Expose
    private String providerTransactionId;
    @SerializedName("autoCommit")
    @Expose
    private String autoCommit;
    @SerializedName("commitState")
    @Expose
    private String commitState;
    @SerializedName("created")
    @Expose
    private String created;
    private final static long serialVersionUID = -5390965087522479439L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PaymentLinkRelatedTransactionDto() {
    }

    /**
     * 
     * @param autoCommit
     * @param providerTransactionId
     * @param transactionId
     * @param created
     * @param userId
     * @param resultCode
     * @param commitState
     * @param anum
     * @param resultMessage
     * @param orderId
     */
    public PaymentLinkRelatedTransactionDto(String transactionId, String resultCode, String resultMessage, String anum, String orderId, String userId, String providerTransactionId, String autoCommit, String commitState, String created) {
        super();
        this.transactionId = transactionId;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.anum = anum;
        this.orderId = orderId;
        this.userId = userId;
        this.providerTransactionId = providerTransactionId;
        this.autoCommit = autoCommit;
        this.commitState = commitState;
        this.created = created;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentLinkRelatedTransactionDto withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public PaymentLinkRelatedTransactionDto withResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public PaymentLinkRelatedTransactionDto withResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
        return this;
    }

    public Object getAnum() {
        return anum;
    }

    public void setAnum(String anum) {
        this.anum = anum;
    }

    public PaymentLinkRelatedTransactionDto withAnum(String anum) {
        this.anum = anum;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentLinkRelatedTransactionDto withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PaymentLinkRelatedTransactionDto withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getProviderTransactionId() {
        return providerTransactionId;
    }

    public void setProviderTransactionId(String providerTransactionId) {
        this.providerTransactionId = providerTransactionId;
    }

    public PaymentLinkRelatedTransactionDto withProviderTransactionId(String providerTransactionId) {
        this.providerTransactionId = providerTransactionId;
        return this;
    }

    public String getAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public PaymentLinkRelatedTransactionDto withAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
        return this;
    }

    public String getCommitState() {
        return commitState;
    }

    public void setCommitState(String commitState) {
        this.commitState = commitState;
    }

    public PaymentLinkRelatedTransactionDto withCommitState(String commitState) {
        this.commitState = commitState;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public PaymentLinkRelatedTransactionDto withCreated(String created) {
        this.created = created;
        return this;
    }

}
