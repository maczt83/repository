package progmatic.bookingmanager.dtos;

public class PaymentLinkRequestToServerDto {
    
    private final String storeName = "sdk_test";
    private String providerName;
    private String amount;    
    private String currency;
    private String language;
    private final String notificationEmail = "bookingproj123@gmail.com";
    private final String emailNotificationOnlySuccess = "true";
    private String orderId;
    private String userId;
    private String extra;

    public PaymentLinkRequestToServerDto(PaymentLinkRequestToBackendDto plrbdto, String userId) {
        this.providerName = plrbdto.getProviderName();
        this.amount = plrbdto.getAmount();
        this.currency = plrbdto.getCurrency();
        this.language = plrbdto.getLanguage();
        this.orderId = plrbdto.getBookingId();
        this.userId = userId;
        this.extra = extraFieldCreation(plrbdto);
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId() {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId = userId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    private String extraFieldCreation(PaymentLinkRequestToBackendDto plrbdto) {
        if (plrbdto.getProviderName().equals("KHBSZEP")) return "KhbCardPocketId:"+plrbdto.getExtra();
        else if (plrbdto.getProviderName().equals("MKBSZEP")) return "mkbSzepCardNumber:"+plrbdto.getExtra()+",mkbSzepCvv:";
        else return null;
    }
    
}
