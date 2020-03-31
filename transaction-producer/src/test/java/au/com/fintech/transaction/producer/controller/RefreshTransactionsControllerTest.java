package au.com.fintech.transaction.producer.controller;

import au.com.fintech.transaction.producer.service.TransactionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;

import static au.com.fintech.transaction.producer.utils.ReflectionHelper.setStaticFinalField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

//@RunWith(MockitoJUnitRunner.class)
public class RefreshTransactionsControllerTest {

    private RefreshTransactionsController controller;

    @Mock
    private TransactionService service;

    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        controller = new RefreshTransactionsController(service);
        setStaticFinalField(controller.getClass(), "LOGGER", logger);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void refresh() {
        for (final boolean refreshFlag : Arrays.asList(true, false)) {
            given(service.readAndSaveTransactions()).willReturn(refreshFlag);
            final String refreshStatus = controller.refresh();

            verify(service).readAndSaveTransactions();
            assertEquals("Transactions Refresh Complete", refreshStatus);
            verify(logger).info("Starting to refresh Transactions");

            if (refreshFlag) {
                verify(logger).info("Transaction refresh successful");
            } else {
                verify(logger).error("Transaction refresh failed, please try again later");
            }
            reset(logger, service);
        }
    }
}