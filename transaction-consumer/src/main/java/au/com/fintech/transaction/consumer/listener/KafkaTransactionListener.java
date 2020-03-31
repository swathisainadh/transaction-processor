package au.com.fintech.transaction.consumer.listener;

import au.com.fintech.transaction.consumer.service.TransactionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class KafkaTransactionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTransactionListener.class);
    private TransactionParser parser;

    @Autowired
    public KafkaTransactionListener(final TransactionParser parser) {
        this.parser = parser;
    }

    @KafkaListener(topics = "${transaction.topic.name}", groupId = "${consumer.group.name}",
            containerFactory = "kafkaListenerFactory")
    public void consumeTransactionMessage(final String message) {
        LOGGER.info("Message received starting parsing and storing the data");
        if (isNotEmpty(message)) {
            // As we have access to postgres already, using the same for storing the data.
            // For enhanced performance, elasticsearch could be used.
            parser.parseAndSaveIncomingTransaction(message);
            LOGGER.info("Message processing completed");
        } else {
            LOGGER.info("Received Message is empty");
        }
    }
}
