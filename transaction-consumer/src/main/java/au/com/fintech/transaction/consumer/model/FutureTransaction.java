package au.com.fintech.transaction.consumer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class FutureTransaction implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String recordCode;
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String oppositePartyCode;
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private Date expirationDate;
    private String currencyCode;
    private String movementCode;
    private String buySellCode;
    private String quantityLongSign;
    private double quantityLong;
    private String quantityShortSign;
    private double quantityShort;
    private double brokerFee;
    private String brokerFeeDc;
    private String brokerFeeCurrencyCode;
    private double clearingFee;
    private String clearingFeeDc;
    private String clearingFeeCurrencyCode;
    private double commission;
    private String commissionDc;
    private String commissionCurrencyCode;
    private Date transactionDate;
    private String futureReference;
    private String ticketNumber;
    private String externalNumber;
    private double transactionPrice;
    private String traderInitials;
    private String oppositeTraderId;
    private String openCloseCode;

    public FutureTransaction() {
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    public String getOppositePartyCode() {
        return oppositePartyCode;
    }

    public void setOppositePartyCode(String oppositePartyCode) {
        this.oppositePartyCode = oppositePartyCode;
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getMovementCode() {
        return movementCode;
    }

    public void setMovementCode(String movementCode) {
        this.movementCode = movementCode;
    }

    public String getBuySellCode() {
        return buySellCode;
    }

    public void setBuySellCode(String buySellCode) {
        this.buySellCode = buySellCode;
    }

    public String getQuantityLongSign() {
        return quantityLongSign;
    }

    public void setQuantityLongSign(String quantityLongSign) {
        this.quantityLongSign = quantityLongSign;
    }

    public double getQuantityLong() {
        return quantityLong;
    }

    public void setQuantityLong(double quantityLong) {
        this.quantityLong = quantityLong;
    }

    public String getQuantityShortSign() {
        return quantityShortSign;
    }

    public void setQuantityShortSign(String quantityShortSign) {
        this.quantityShortSign = quantityShortSign;
    }

    public double getQuantityShort() {
        return quantityShort;
    }

    public void setQuantityShort(double quantityShort) {
        this.quantityShort = quantityShort;
    }

    public double getBrokerFee() {
        return brokerFee;
    }

    public void setBrokerFee(double brokerFee) {
        this.brokerFee = brokerFee;
    }

    public String getBrokerFeeDc() {
        return brokerFeeDc;
    }

    public void setBrokerFeeDc(String brokerFeeDc) {
        this.brokerFeeDc = brokerFeeDc;
    }

    public String getBrokerFeeCurrencyCode() {
        return brokerFeeCurrencyCode;
    }

    public void setBrokerFeeCurrencyCode(String brokerFeeCurrencyCode) {
        this.brokerFeeCurrencyCode = brokerFeeCurrencyCode;
    }

    public double getClearingFee() {
        return clearingFee;
    }

    public void setClearingFee(double clearingFee) {
        this.clearingFee = clearingFee;
    }

    public String getClearingFeeDc() {
        return clearingFeeDc;
    }

    public void setClearingFeeDc(String clearingFeeDc) {
        this.clearingFeeDc = clearingFeeDc;
    }

    public String getClearingFeeCurrencyCode() {
        return clearingFeeCurrencyCode;
    }

    public void setClearingFeeCurrencyCode(String clearingFeeCurrencyCode) {
        this.clearingFeeCurrencyCode = clearingFeeCurrencyCode;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getCommissionDc() {
        return commissionDc;
    }

    public void setCommissionDc(String commissionDc) {
        this.commissionDc = commissionDc;
    }

    public String getCommissionCurrencyCode() {
        return commissionCurrencyCode;
    }

    public void setCommissionCurrencyCode(String commissionCurrencyCode) {
        this.commissionCurrencyCode = commissionCurrencyCode;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getFutureReference() {
        return futureReference;
    }

    public void setFutureReference(String futureReference) {
        this.futureReference = futureReference;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getTraderInitials() {
        return traderInitials;
    }

    public void setTraderInitials(String traderInitials) {
        this.traderInitials = traderInitials;
    }

    public String getOppositeTraderId() {
        return oppositeTraderId;
    }

    public void setOppositeTraderId(String oppositeTraderId) {
        this.oppositeTraderId = oppositeTraderId;
    }

    public String getOpenCloseCode() {
        return openCloseCode;
    }

    public void setOpenCloseCode(String openCloseCode) {
        this.openCloseCode = openCloseCode;
    }

    @Override
    public String toString() {
        return "FutureTransaction{" +
                "recordCode='" + recordCode + '\'' +
                ", clientType='" + clientType + '\'' +
                ", clientNumber='" + clientNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", subAccountNumber='" + subAccountNumber + '\'' +
                ", oppositePartyCode='" + oppositePartyCode + '\'' +
                ", productGroupCode='" + productGroupCode + '\'' +
                ", exchangeCode='" + exchangeCode + '\'' +
                ", symbol='" + symbol + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", movementCode='" + movementCode + '\'' +
                ", buySellCode='" + buySellCode + '\'' +
                ", quantityLongSign='" + quantityLongSign + '\'' +
                ", quantityLong=" + quantityLong +
                ", quantityShortSign='" + quantityShortSign + '\'' +
                ", quantityShort=" + quantityShort +
                ", brokerFee=" + brokerFee +
                ", brokerFee1=" + brokerFeeDc +
                ", brokerFeeCurrencyCode='" + brokerFeeCurrencyCode + '\'' +
                ", clearingFee=" + clearingFee +
                ", clearingFee1=" + clearingFeeDc +
                ", clearingFeeCurrencyCode='" + clearingFeeCurrencyCode + '\'' +
                ", commission=" + commission +
                ", commissionDc=" + commissionDc +
                ", commissionCurrencyCode='" + commissionCurrencyCode + '\'' +
                ", transactionDate=" + transactionDate +
                ", futureReference='" + futureReference + '\'' +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", externalNumber='" + externalNumber + '\'' +
                ", transactionPrice=" + transactionPrice +
                ", traderInitials='" + traderInitials + '\'' +
                ", oppositeTraderId='" + oppositeTraderId + '\'' +
                ", openCloseCode='" + openCloseCode + '\'' +
                '}';
    }
}
