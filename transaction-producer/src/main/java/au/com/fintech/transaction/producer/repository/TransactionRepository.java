package au.com.fintech.transaction.producer.repository;

import au.com.fintech.transaction.producer.model.ClientTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<ClientTransaction, Long>, CustomTransactionRepository {

}
