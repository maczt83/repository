
package progmatic.bookingmanager.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentLinkDetailsDto implements Serializable
{

    @SerializedName("CommonData")
    @Expose
    private String commonData;
    @SerializedName("RelatedTransactions")
    @Expose
    private List<PaymentLinkRelatedTransactionDto> relatedTransactions = null;
    @SerializedName("ResultCode")
    @Expose
    private String resultCode;
    @SerializedName("ResultMessage")
    @Expose
    private String resultMessage;
    private final static long serialVersionUID = -7411221810844563501L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PaymentLinkDetailsDto() {
    }

    /**
     * 
     * @param resultCode
     * @param commonData
     * @param resultMessage
     * @param relatedTransactions
     */
    public PaymentLinkDetailsDto(String commonData, List<PaymentLinkRelatedTransactionDto> relatedTransactions, String resultCode, String resultMessage) {
        super();
        this.commonData = commonData;
        this.relatedTransactions = relatedTransactions;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getCommonData() {
        return commonData;
    }

    public void setCommonData(String commonData) {
        this.commonData = commonData;
    }

    public PaymentLinkDetailsDto withCommonData(String commonData) {
        this.commonData = commonData;
        return this;
    }

    public List<PaymentLinkRelatedTransactionDto> getRelatedTransactions() {
        return relatedTransactions;
    }

    public void setRelatedTransactions(List<PaymentLinkRelatedTransactionDto> relatedTransactions) {
        this.relatedTransactions = relatedTransactions;
    }

    public PaymentLinkDetailsDto withRelatedTransactions(List<PaymentLinkRelatedTransactionDto> relatedTransactions) {
        this.relatedTransactions = relatedTransactions;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public PaymentLinkDetailsDto withResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public PaymentLinkDetailsDto withResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
        return this;
    }

}
