package au.com.fintech.transaction.producer.init;


import au.com.fintech.transaction.producer.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;

import static au.com.fintech.transaction.producer.utils.ReflectionHelper.setStaticFinalField;
import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProducerApplicationEventListenerTest {

    @Mock private TransactionService service;
    @Mock private Logger logger;

    private ProducerApplicationEventListener listener;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        listener = new ProducerApplicationEventListener(service);
        setStaticFinalField(listener.getClass(), "LOGGER", logger);
    }

    @Test
    public void onApplicationEvent() {

        for (final boolean refreshFlag : asList(true, false)) {
            given(service.readAndSaveTransactions()).willReturn(refreshFlag);
            final ContextRefreshedEvent event = mock(ContextRefreshedEvent.class);
            listener.onApplicationEvent(event);
            verify(logger).info("Kafka Producer Application has started, triggering transactions refresh and publish messages");

            if (refreshFlag) {
                verify(service).publishTransactions();
            }

            reset(service, logger);
        }
    }
}