package au.com.fintech.transaction.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionSummary {

    @JsonProperty("Client_Information")
    private String clientInformation;

    @JsonProperty("Product_Information")
    private String productInformation;

    @JsonProperty("Total_Transaction_Amount")
    private String totalTransactionAmount;

    public TransactionSummary(String clientInformation, String productInformation, String totalTransactionAmount) {
        this.clientInformation = clientInformation;
        this.productInformation = productInformation;
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public String getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(String clientInformation) {
        this.clientInformation = clientInformation;
    }

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public String getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(String totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }
}
