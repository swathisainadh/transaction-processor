package au.com.fintech.transaction.producer.service;

public interface TransactionService {

    boolean readAndSaveTransactions();

    void publishTransactions();
}
