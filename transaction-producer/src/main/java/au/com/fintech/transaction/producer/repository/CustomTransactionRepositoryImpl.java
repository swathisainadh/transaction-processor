package au.com.fintech.transaction.producer.repository;

import au.com.fintech.transaction.producer.model.ClientTransaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static au.com.fintech.transaction.producer.model.MessageStatus.NEW;

// Test for this Implementation should be covered under Integration Tests Module
@Repository
public class CustomTransactionRepositoryImpl implements CustomTransactionRepository {

    @PersistenceContext
    private EntityManager em;

    public CustomTransactionRepositoryImpl() {
    }

    @Override
    public List<ClientTransaction> findNewTransactions() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ClientTransaction> cq = cb.createQuery(ClientTransaction.class);

        final Root<ClientTransaction> root = cq.from(ClientTransaction.class);
        final Predicate authorNamePredicate = cb.equal(root.get("status"), NEW);
        cq.where(authorNamePredicate);

        final TypedQuery<ClientTransaction> query = em.createQuery(cq);
        return query.getResultList();
    }
}
