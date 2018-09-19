package progmatic.bookingmanager.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PaymentLinkRequestToBackendDto {
    
    @NotNull
    private String bookingId;
    @NotNull
    @Pattern(regexp = "^OTPSimple$|^KHB$|^KHBSZEP$|^OTPSZEP$|^MKBSZEP$")
    private String providerName;
    @NotNull
    @Pattern(regexp = "^\\d*$")
    private String amount;    
    @NotNull
    @Pattern(regexp = "^HUF$|^EUR$|^USD$")
    private String currency;
    @NotNull
    @Pattern(regexp = "^HU$|^EN$|^DE$")
    private String language;
    
    private String extra;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    
}
