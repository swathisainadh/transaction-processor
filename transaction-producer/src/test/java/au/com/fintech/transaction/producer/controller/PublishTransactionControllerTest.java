package au.com.fintech.transaction.producer.controller;

import au.com.fintech.transaction.producer.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static au.com.fintech.transaction.producer.utils.ReflectionHelper.setStaticFinalField;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

class PublishTransactionControllerTest {

    private PublishTransactionController controller;

    @Mock
    private TransactionService service;

    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        controller = new PublishTransactionController(service);
        setStaticFinalField(controller.getClass(), "LOGGER", logger);
    }

    @Test
    void shouldPublishTransactions() {
        assertEquals("Transactions Published successfully", controller.publishTransactions());
        verify(logger).info("Starting to publish new Transaction messages");
        verify(logger).info("Publishing new Transaction messages completed");
    }
}