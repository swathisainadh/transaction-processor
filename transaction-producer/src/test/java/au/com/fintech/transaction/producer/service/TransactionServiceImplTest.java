package au.com.fintech.transaction.producer.service;

import au.com.fintech.transaction.producer.model.ClientTransaction;
import au.com.fintech.transaction.producer.model.MessageStatus;
import au.com.fintech.transaction.producer.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

import static au.com.fintech.transaction.producer.utils.ReflectionHelper.setStaticFinalField;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TransactionServiceImpl.class)
public class TransactionServiceImplTest {

    @Mock
    private Logger logger;

    @Mock private TransactionRepository repository;
    @Mock private KafkaTemplate<String, String> template;

    private TransactionService service;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        service = new TransactionServiceImpl(repository, template, "test-topic");
        setStaticFinalField(service.getClass(), "LOGGER", logger);
    }

    @Test
    public void shouldReadAndSaveTransactions() {
        final boolean refreshStatus = service.readAndSaveTransactions();
        assertTrue(refreshStatus);
        final ArgumentCaptor<List<ClientTransaction>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(repository).saveAll(argumentCaptor.capture());

        final List<ClientTransaction> savedTransactions = argumentCaptor.getValue();
        assertEquals(717, savedTransactions.size());

        final List<ClientTransaction> transactionsWithNewStatus = savedTransactions.stream()
                .filter(clientTransaction -> clientTransaction.getStatus().equals(MessageStatus.NEW))
                .collect(Collectors.toList());

        assertEquals(717, transactionsWithNewStatus.size());
        verify(logger).info("Transactions have been fetched from Input.txt and persisted in Database");
    }

    @Test
    public void shouldHandleExceptionWhileParsingTransactions() throws Exception {
        whenNew(BufferedReader.class).withAnyArguments().thenReturn(null);
        final boolean refreshStatus = service.readAndSaveTransactions();
        assertFalse(refreshStatus);
        verifyNoInteractions(repository);
        verify(logger).error(eq("Exception occurred while reading the file"), any(Exception.class));
    }

    @Test
    public void shouldPublishTransactions() {
        given(repository.findNewTransactions()).willReturn(emptyList());
        service.publishTransactions();
        verifyNoInteractions(template);
        verify(logger).info("No New Transactions have been found");

        final ClientTransaction transaction1 = new ClientTransaction("Message 1");
        final ClientTransaction transaction2 = new ClientTransaction("Message 2");
        given(repository.findNewTransactions()).willReturn(asList(transaction1, transaction2));
        service.publishTransactions();
        verify(logger).info("2 New Transactions have been fetched from Database and starting to publish");
        verify(template, times(2)).send(eq("test-topic"), any(String.class));
        final ArgumentCaptor<List<ClientTransaction>> clientTransactionArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(repository).saveAll(clientTransactionArgumentCaptor.capture());

        final List<ClientTransaction> updatedTransactionList = clientTransactionArgumentCaptor.getValue();
        assertEquals(2, updatedTransactionList.size());
        assertEquals(MessageStatus.SENT, updatedTransactionList.get(0).getStatus());
        assertEquals(MessageStatus.SENT, updatedTransactionList.get(1).getStatus());
    }
}