package au.com.fintech.transaction.consumer.service;

import au.com.fintech.transaction.consumer.model.FutureTransaction;
import au.com.fintech.transaction.consumer.repository.FutureTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static au.com.fintech.transaction.consumer.utils.ReflectionHelper.setStaticFinalField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class TransactionParserImplTest {

    private static final String VALID_MESSAGE = "315CL  123400020001SGXDC FUSGX NK    20100910JPY01B 0000000004 0000000000000000000240DUSD000000000120DUSD000000000000DJPY201008200013110     688998000092250000000             O";
    private static final String INVALID_MESSAGE = "315CL  123400020001SGXDC FUSGX NK    20100X10JPY01B 0000000004 0000000000000000000240DUSD000000000120DUSD000000000000DJPY201008200013110     688998000092250000000             O";

    @Mock
    private FutureTransactionRepository repository;

    @Mock
    private Logger logger;

    private TransactionParser parser;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        parser = new TransactionParserImpl(repository);
        setStaticFinalField(parser.getClass(), "LOGGER", logger);
    }

    @Test
    void parseAndHandleInvalidMessage() {
        parser.parseAndSaveIncomingTransaction(INVALID_MESSAGE);
        verify(logger).error(eq("Error parsing transaction message"), any(Exception.class));
        verifyNoInteractions(repository);
    }

    @Test
    void parseAndSaveValidMessage() throws ParseException {

        final Date expectedExpirationDate = new SimpleDateFormat("yyyyMMdd").parse("20100910");
        final Date expectedTransactionDate = new SimpleDateFormat("yyyyMMdd").parse("20100820");
        final ArgumentCaptor<FutureTransaction> futureTransactionArgumentCaptor = ArgumentCaptor.forClass(FutureTransaction.class);
        parser.parseAndSaveIncomingTransaction(VALID_MESSAGE);
        verify(repository).save(futureTransactionArgumentCaptor.capture());
        final FutureTransaction savedTransaction = futureTransactionArgumentCaptor.getValue();
        assertNotNull(savedTransaction);
        assertEquals("315", savedTransaction.getRecordCode());
        assertEquals("CL", savedTransaction.getClientType());
        assertEquals("1234", savedTransaction.getClientNumber());
        assertEquals("0002", savedTransaction.getAccountNumber());
        assertEquals("0001", savedTransaction.getSubAccountNumber());
        assertEquals("SGXDC", savedTransaction.getOppositePartyCode());
        assertEquals("FU", savedTransaction.getProductGroupCode());
        assertEquals("SGX", savedTransaction.getExchangeCode());
        assertEquals("NK", savedTransaction.getSymbol());
        assertEquals(expectedExpirationDate, savedTransaction.getExpirationDate());
        assertEquals("JPY", savedTransaction.getCurrencyCode());
        assertEquals("01", savedTransaction.getMovementCode());
        assertEquals("B", savedTransaction.getBuySellCode());
        assertEquals("", savedTransaction.getQuantityLongSign());
        assertEquals(4.0, savedTransaction.getQuantityLong());
        assertEquals("", savedTransaction.getQuantityShortSign());
        assertEquals(0.0, savedTransaction.getQuantityShort());
        assertEquals(240.0, savedTransaction.getBrokerFee());
        assertEquals("D", savedTransaction.getBrokerFeeDc());
        assertEquals("USD", savedTransaction.getBrokerFeeCurrencyCode());
        assertEquals(120.0, savedTransaction.getClearingFee());
        assertEquals("D", savedTransaction.getClearingFeeDc());
        assertEquals("USD", savedTransaction.getClearingFeeCurrencyCode());
        assertEquals(0, savedTransaction.getCommission());
        assertEquals("D", savedTransaction.getCommissionDc());
        assertEquals("JPY", savedTransaction.getCommissionCurrencyCode());
        assertEquals(expectedTransactionDate, savedTransaction.getTransactionDate());
        assertEquals("001311", savedTransaction.getFutureReference());
        assertEquals("0", savedTransaction.getTicketNumber());
        assertEquals("688998", savedTransaction.getExternalNumber());
        assertEquals(9.225E10, savedTransaction.getTransactionPrice());
        assertEquals("", savedTransaction.getTraderInitials());
        assertEquals("", savedTransaction.getOppositeTraderId());
        assertEquals("O", savedTransaction.getOpenCloseCode());

        verifyNoInteractions(logger);
    }
}