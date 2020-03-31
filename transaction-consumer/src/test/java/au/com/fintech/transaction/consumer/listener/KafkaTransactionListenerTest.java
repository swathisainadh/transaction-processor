package au.com.fintech.transaction.consumer.listener;

import au.com.fintech.transaction.consumer.service.TransactionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static au.com.fintech.transaction.consumer.utils.ReflectionHelper.setStaticFinalField;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class KafkaTransactionListenerTest {

    @Mock
    private TransactionParser parser;

    @Mock
    private Logger logger;

    private KafkaTransactionListener listener;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        listener = new KafkaTransactionListener(parser);
        setStaticFinalField(listener.getClass(), "LOGGER", logger);
    }

    @Test
    void consumeTransactionMessageAndDoNotParseIfEmpty() {
        listener.consumeTransactionMessage("");
        verify(logger).info("Message received starting parsing and storing the data");
        verify(logger).info("Received Message is empty");
        verifyNoInteractions(parser);
    }

    @Test
    void consumeTransactionMessageAndParseMessage() {
        listener.consumeTransactionMessage("Sample Test Message");
        verify(logger).info("Message received starting parsing and storing the data");
        verify(parser).parseAndSaveIncomingTransaction("Sample Test Message");
        verify(logger).info("Message processing completed");
    }
}