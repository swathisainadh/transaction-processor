package au.com.fintech.transaction.producer.controller;

import au.com.fintech.transaction.producer.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("refresh")
public class RefreshTransactionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshTransactionsController.class);

    private TransactionService transactionService;

    @Autowired
    public RefreshTransactionsController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String refresh() {
        LOGGER.info("Starting to refresh Transactions");
        final boolean refreshResult = transactionService.readAndSaveTransactions();
        if (refreshResult) {
            LOGGER.info("Transaction refresh successful");
        } else {
            LOGGER.error("Transaction refresh failed, please try again later");
        }
        return "Transactions Refresh Complete";
    }
}
