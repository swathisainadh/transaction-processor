package au.com.fintech.transaction.producer.controller;

import au.com.fintech.transaction.producer.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publish")
public class PublishTransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishTransactionController.class);

    private TransactionService service;


    @Autowired
    public PublishTransactionController(final TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public String publishTransactions() {
        LOGGER.info("Starting to publish new Transaction messages");
        service.publishTransactions();
        LOGGER.info("Publishing new Transaction messages completed");

        return "Transactions Published successfully";
    }
}

