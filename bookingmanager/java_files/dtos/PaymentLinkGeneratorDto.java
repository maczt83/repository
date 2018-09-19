package progmatic.bookingmanager.dtos;

import com.google.gson.annotations.SerializedName;

public class PaymentLinkGeneratorDto {

    @SerializedName("PaymentLinkName")
    private String paymentLinkName;
    @SerializedName("PaymentLinkUrl")
    private String paymentLinkUrl;
    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ProviderName")
    private String providerName;
    @SerializedName("Amount")
    private String amount;
    @SerializedName("Currency")
    private String currency;
    @SerializedName("Language")
    private String language;
    @SerializedName("OrderId")
    private String orderId;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("AutoCommit")
    private String autoCommit;
    @SerializedName("ExpirationTime")
    private String expirationTime;
    @SerializedName("notificationUrl")
    private String notificationUrl;
    @SerializedName("NotificationEmail")
    private String notificationEmail;
    @SerializedName("EmailNotificationOnlySuccess")
    private String emailNotificationOnlySuccess;
    @SerializedName("EmailNotificationTime")
    private String emailNotificationTime;
    @SerializedName("Extra")
    private String extra;
    @SerializedName("Status")
    private String status;
    @SerializedName("Created")
    private String created;
    @SerializedName("LastModified")
    private String lastModified;
    
    public PaymentLinkGeneratorDto(String paymentLinkName, String paymentLinkUrl, String resultCode, String resultMessage, String providerName, String amount, String currency, String language, String orderId, String userId, String autoCommit, String expirationTime, String notificationUrl, String notificationEmail, String emailNotificationOnlySuccess, String emailNotificationTime, String extra, String status, String created, String lastModified) {
        this.paymentLinkName = paymentLinkName;
        this.paymentLinkUrl = paymentLinkUrl;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.providerName = providerName;
        this.amount = amount;
        this.currency = currency;
        this.language = language;
        this.orderId = orderId;
        this.userId = userId;
        this.autoCommit = autoCommit;
        this.expirationTime = expirationTime;
        this.notificationUrl = notificationUrl;
        this.notificationEmail = notificationEmail;
        this.emailNotificationOnlySuccess = emailNotificationOnlySuccess;
        this.emailNotificationTime = emailNotificationTime;
        this.extra = extra;
        this.status = status;
        this.created = created;
        this.lastModified = lastModified;
    }
    
    public PaymentLinkGeneratorDto(){
        
    }
    
    public String getPaymentLinkName() {
        return paymentLinkName;
    }

    public String getPaymentLinkUrl() {
        return paymentLinkUrl;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLanguage() {
        return language;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAutoCommit() {
        return autoCommit;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public String getEmailNotificationOnlySuccess() {
        return emailNotificationOnlySuccess;
    }

    public String getEmailNotificationTime() {
        return emailNotificationTime;
    }

    public String getExtra() {
        return extra;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated() {
        return created;
    }

    public String getLastModified() {
        return lastModified;
    }  

    public void setPaymentLinkName(String paymentLinkName) {
        this.paymentLinkName = paymentLinkName;
    }

    public void setPaymentLinkUrl(String paymentLinkUrl) {
        this.paymentLinkUrl = paymentLinkUrl;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public void setEmailNotificationOnlySuccess(String emailNotificationOnlySuccess) {
        this.emailNotificationOnlySuccess = emailNotificationOnlySuccess;
    }

    public void setEmailNotificationTime(String emailNotificationTime) {
        this.emailNotificationTime = emailNotificationTime;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

}
