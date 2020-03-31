package au.com.fintech.transaction.consumer.service;

import au.com.fintech.transaction.consumer.model.FutureTransaction;
import au.com.fintech.transaction.consumer.model.FutureTransactionBuilder;
import au.com.fintech.transaction.consumer.repository.FutureTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@Service
public class TransactionParserImpl implements TransactionParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionParserImpl.class);

    private FutureTransactionRepository repository;

    @Autowired
    public TransactionParserImpl(FutureTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void parseAndSaveIncomingTransaction(final String message) {
        final FutureTransaction futureTransaction = parseMessage(message);
        if (futureTransaction != null) {
            repository.save(futureTransaction);
        }
    }

    private FutureTransaction parseMessage(final String message) {
        FutureTransaction futureTransaction = null;
        try {
            futureTransaction = new FutureTransactionBuilder()
                    .withRecordCode(message.substring(0, 3).trim())
                    .withClientType(message.substring(3, 7).trim())
                    .withClientNumber(message.substring(7, 11).trim())
                    .withAccountNumber(message.substring(11, 15).trim())
                    .withSubAccountNumber(message.substring(15, 19).trim())
                    .withOppositePartyCode(message.substring(19, 25).trim())
                    .withProductGroupCode(message.substring(25, 27).trim())
                    .withExchangeCode(message.substring(27, 31).trim())
                    .withSymbol(message.substring(31, 37).trim())
                    .withExpirationDate(new SimpleDateFormat("yyyyMMdd").parse(message.substring(37, 45).trim()))
                    .withCurrencyCode(message.substring(45, 48).trim())
                    .withMovementCode(message.substring(48, 50).trim())
                    .withBuySellCode(message.substring(50, 51).trim())
                    .withQuantityLongSign(message.substring(51, 52).trim())
                    .withQuantityLong(Double.parseDouble(message.substring(52, 62).trim()))
                    .withQuantityShortSign(message.substring(62, 63).trim())
                    .withQuantityShort(Double.parseDouble(message.substring(63, 73).trim()))
                    .withBrokerFee(Double.parseDouble(message.substring(73, 85).trim()))
                    .withBrokerFeeDc(message.substring(85, 86).trim())
                    .withBrokerFeeCurrencyCode(message.substring(86, 89).trim())
                    .withClearingFee(Double.parseDouble(message.substring(89, 101).trim()))
                    .withClearingFeeDc(message.substring(101, 102).trim())
                    .withClearingFeeCurrencyCode(message.substring(102, 105).trim())
                    .withCommission(Double.parseDouble(message.substring(105, 117).trim()))
                    .withCommissionDc(message.substring(117, 118).trim())
                    .withCommissionCurrencyCode(message.substring(118, 121).trim())
                    .withTransactionDate(new SimpleDateFormat("yyyyMMdd").parse(message.substring(121, 129).trim()))
                    .withFutureReference(message.substring(129, 135).trim())
                    .withTicketNumber(message.substring(135, 141).trim())
                    .withExternalNumber(message.substring(141, 147).trim())
                    .withTransactionPrice(Double.parseDouble(message.substring(147, 162).trim()))
                    .withTraderInitials(message.substring(162, 168).trim())
                    .withOppositeTraderId(message.substring(168, 175).trim())
                    .withOpenCloseCode(message.substring(175, 176).trim())
                    .build();
        } catch (Exception e) {
            LOGGER.error("Error parsing transaction message", e);
        }
        return futureTransaction;
    }
}
