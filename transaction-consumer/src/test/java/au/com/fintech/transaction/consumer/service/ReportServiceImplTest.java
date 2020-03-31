package au.com.fintech.transaction.consumer.service;

import au.com.fintech.transaction.consumer.model.FutureTransaction;
import au.com.fintech.transaction.consumer.model.FutureTransactionBuilder;
import au.com.fintech.transaction.consumer.model.TransactionSummary;
import au.com.fintech.transaction.consumer.repository.FutureTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class ReportServiceImplTest {

    private ReportService service;

    @Mock private FutureTransactionRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new ReportServiceImpl(repository);
    }

    @Test
    void buildClientTransactionSummaries() {
        final Date newDate = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String expirationDateDisplayValue = sdf.format(newDate);
        final FutureTransaction futureTransaction1 = new FutureTransactionBuilder().withTransactionDate(newDate)
                .withProductGroupCode("PR 1").withSymbol("C").withExpirationDate(newDate).withExchangeCode("X")
                .withClientNumber("0001").withClientType("CL").withAccountNumber("0008").withSubAccountNumber("0009")
                .withQuantityLong(89).withQuantityShort(87)
                .build();
        final FutureTransaction futureTransaction2 = new FutureTransactionBuilder().withTransactionDate(newDate)
                .withProductGroupCode("PR 2").withSymbol("D").withExpirationDate(newDate).withExchangeCode("X")
                .withClientNumber("0001").withClientType("CL").withAccountNumber("0006").withSubAccountNumber("0007")
                .withQuantityLong(89).withQuantityShort(78)
                .build();
        given(repository.findAll()).willReturn(asList(futureTransaction1, futureTransaction2));
        final List<TransactionSummary> transactionSummaryList = service.buildClientTransactionSummaries();

        assertEquals(2, transactionSummaryList.size());

        final TransactionSummary actualSummary1 = transactionSummaryList.get(0);
        assertEquals("CL : 0001 : 0008 : 0009", actualSummary1.getClientInformation());
        assertEquals("X : PR 1 : C : " + expirationDateDisplayValue, actualSummary1.getProductInformation());
        assertEquals("$2.0", actualSummary1.getTotalTransactionAmount());

        final TransactionSummary actualSummary2 = transactionSummaryList.get(1);
        assertEquals("CL : 0001 : 0006 : 0007", actualSummary2.getClientInformation());
        assertEquals("X : PR 2 : D : " + expirationDateDisplayValue, actualSummary2.getProductInformation());
        assertEquals("$11.0", actualSummary2.getTotalTransactionAmount());
    }
}