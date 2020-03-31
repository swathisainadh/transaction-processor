package au.com.fintech.transaction.producer.service;

import au.com.fintech.transaction.producer.model.ClientTransaction;
import au.com.fintech.transaction.producer.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static au.com.fintech.transaction.producer.model.MessageStatus.SENT;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    public static final String TRANSACTIONS_INPUT_TXT = "src/main/resources/transactions/Input.txt";

    private TransactionRepository repository;
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topicName;

    @Autowired
    public TransactionServiceImpl(final TransactionRepository repository,
                                  final KafkaTemplate<String, String> kafkaTemplate,
                                  @Value("${publish.transaction.kafka.topic.name}") final String topicName) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    @Transactional
    public boolean readAndSaveTransactions() {
        final List<ClientTransaction> clientTransactionList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_INPUT_TXT))) {
            reader.lines().forEach(line -> clientTransactionList.add(new ClientTransaction(line)));
            repository.saveAll(clientTransactionList);
            LOGGER.info("Transactions have been fetched from Input.txt and persisted in Database");
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception occurred while reading the file", e);
        }
        return false;
    }

    @Override
    @Transactional
    public void publishTransactions() {
        // For publishing multiple messages, java concurrency can be used (for eg., CountDownLatch for throttling/ limiting number of messages per request
        final List<ClientTransaction> clientTransactionList = repository.findNewTransactions();
        if (clientTransactionList.isEmpty()) {
            LOGGER.info("No New Transactions have been found");
        } else {
            LOGGER.info(String.format("%s New Transactions have been fetched from Database and starting to publish", clientTransactionList.size()));
            clientTransactionList.forEach(transaction -> {
                kafkaTemplate.send(topicName, transaction.getMessage());
                transaction.setStatus(SENT);
            });

            repository.saveAll(clientTransactionList);
            LOGGER.info(String.format("%s New Transactions have been published and marked done successfully", clientTransactionList.size()));
        }
    }
}
