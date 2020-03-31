package au.com.fintech.transaction.consumer.service;

public interface TransactionParser {

    void parseAndSaveIncomingTransaction(final String message);
}
