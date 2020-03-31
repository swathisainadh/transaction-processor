package au.com.fintech.transaction.consumer.model;

import java.util.Date;

public class FutureTransactionBuilder {

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

    public FutureTransactionBuilder withRecordCode(String recordCode) {
        this.recordCode = recordCode;
        return this;
    }

    public FutureTransactionBuilder withClientType(String clientType) {
        this.clientType = clientType;
        return this;
    }

    public FutureTransactionBuilder withClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
        return this;
    }

    public FutureTransactionBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public FutureTransactionBuilder withSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
        return this;
    }

    public FutureTransactionBuilder withOppositePartyCode(String oppositePartyCode) {
        this.oppositePartyCode = oppositePartyCode;
        return this;
    }

    public FutureTransactionBuilder withProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
        return this;
    }

    public FutureTransactionBuilder withExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
        return this;
    }

    public FutureTransactionBuilder withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public FutureTransactionBuilder withExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public FutureTransactionBuilder withCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public FutureTransactionBuilder withMovementCode(String movementCode) {
        this.movementCode = movementCode;
        return this;
    }

    public FutureTransactionBuilder withBuySellCode(String buySellCode) {
        this.buySellCode = buySellCode;
        return this;
    }

    public FutureTransactionBuilder withQuantityLongSign(String quantityLongSign) {
        this.quantityLongSign = quantityLongSign;
        return this;
    }

    public FutureTransactionBuilder withQuantityLong(double quantityLong) {
        this.quantityLong = quantityLong;
        return this;
    }

    public FutureTransactionBuilder withQuantityShortSign(String quantityShortSign) {
        this.quantityShortSign = quantityShortSign;
        return this;
    }

    public FutureTransactionBuilder withQuantityShort(double quantityShort) {
        this.quantityShort = quantityShort;
        return this;
    }

    public FutureTransactionBuilder withBrokerFee(double brokerFee) {
        this.brokerFee = brokerFee;
        return this;
    }

    public FutureTransactionBuilder withBrokerFeeDc(String brokerFeeDc) {
        this.brokerFeeDc = brokerFeeDc;
        return this;
    }

    public FutureTransactionBuilder withBrokerFeeCurrencyCode(String brokerFeeCurrencyCode) {
        this.brokerFeeCurrencyCode = brokerFeeCurrencyCode;
        return this;
    }

    public FutureTransactionBuilder withClearingFee(double clearingFee) {
        this.clearingFee = clearingFee;
        return this;
    }

    public FutureTransactionBuilder withClearingFeeDc(String clearingFeeDc) {
        this.clearingFeeDc = clearingFeeDc;
        return this;
    }

    public FutureTransactionBuilder withClearingFeeCurrencyCode(String clearingFeeCurrencyCode) {
        this.clearingFeeCurrencyCode = clearingFeeCurrencyCode;
        return this;
    }

    public FutureTransactionBuilder withCommission(double commission) {
        this.commission = commission;
        return this;
    }

    public FutureTransactionBuilder withCommissionDc(String commissionDc) {
        this.commissionDc = commissionDc;
        return this;
    }

    public FutureTransactionBuilder withCommissionCurrencyCode(String commissionCurrencyCode) {
        this.commissionCurrencyCode = commissionCurrencyCode;
        return this;
    }

    public FutureTransactionBuilder withTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public FutureTransactionBuilder withFutureReference(String futureReference) {
        this.futureReference = futureReference;
        return this;
    }

    public FutureTransactionBuilder withTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
        return this;
    }

    public FutureTransactionBuilder withExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
        return this;
    }

    public FutureTransactionBuilder withTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
        return this;
    }

    public FutureTransactionBuilder withTraderInitials(String traderInitials) {
        this.traderInitials = traderInitials;
        return this;
    }

    public FutureTransactionBuilder withOppositeTraderId(String oppositeTraderId) {
        this.oppositeTraderId = oppositeTraderId;
        return this;
    }

    public FutureTransactionBuilder withOpenCloseCode(String openCloseCode) {
        this.openCloseCode = openCloseCode;
        return this;
    }

    public FutureTransaction build() {
        final FutureTransaction futureTransaction = new FutureTransaction();
        futureTransaction.setAccountNumber(accountNumber);
        futureTransaction.setBrokerFee(brokerFee);
        futureTransaction.setBrokerFeeDc(brokerFeeDc);
        futureTransaction.setBrokerFeeCurrencyCode(brokerFeeCurrencyCode);
        futureTransaction.setBuySellCode(buySellCode);
        futureTransaction.setClearingFee(clearingFee);
        futureTransaction.setClearingFeeDc(clearingFeeDc);
        futureTransaction.setClearingFeeCurrencyCode(clearingFeeCurrencyCode);
        futureTransaction.setClientNumber(clientNumber);
        futureTransaction.setClientType(clientType);
        futureTransaction.setCommission(commission);
        futureTransaction.setCommissionDc(commissionDc);
        futureTransaction.setCommissionCurrencyCode(commissionCurrencyCode);
        futureTransaction.setCurrencyCode(currencyCode);
        futureTransaction.setExchangeCode(exchangeCode);
        futureTransaction.setExpirationDate(expirationDate);
        futureTransaction.setExternalNumber(externalNumber);
        futureTransaction.setFutureReference(futureReference);
        futureTransaction.setMovementCode(movementCode);
        futureTransaction.setOpenCloseCode(openCloseCode);
        futureTransaction.setOppositePartyCode(oppositePartyCode);
        futureTransaction.setOppositeTraderId(oppositeTraderId);
        futureTransaction.setProductGroupCode(productGroupCode);
        futureTransaction.setQuantityLong(quantityLong);
        futureTransaction.setQuantityLongSign(quantityLongSign);
        futureTransaction.setQuantityShort(quantityShort);
        futureTransaction.setQuantityShortSign(quantityShortSign);
        futureTransaction.setRecordCode(recordCode);
        futureTransaction.setSubAccountNumber(subAccountNumber);
        futureTransaction.setSymbol(symbol);
        futureTransaction.setTicketNumber(ticketNumber);
        futureTransaction.setTraderInitials(traderInitials);
        futureTransaction.setTransactionDate(transactionDate);
        futureTransaction.setTransactionPrice(transactionPrice);
        return futureTransaction;
    }
}
