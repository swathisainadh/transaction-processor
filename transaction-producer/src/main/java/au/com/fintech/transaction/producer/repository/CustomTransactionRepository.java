package au.com.fintech.transaction.producer.repository;

import au.com.fintech.transaction.producer.model.ClientTransaction;

import java.util.List;

public interface CustomTransactionRepository {

    List<ClientTransaction> findNewTransactions();
}
