package au.com.fintech.transaction.consumer.repository;

import au.com.fintech.transaction.consumer.model.FutureTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureTransactionRepository extends JpaRepository<FutureTransaction, Long> {
}
