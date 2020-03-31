package au.com.fintech.transaction.consumer.service;

import au.com.fintech.transaction.consumer.model.FutureTransaction;
import au.com.fintech.transaction.consumer.model.TransactionSummary;
import au.com.fintech.transaction.consumer.repository.FutureTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    public static final String COLON_DELIMITER = " : ";
    public static final String DOLLAR_SYMBOL = "$";
    private FutureTransactionRepository repository;

    @Autowired
    public ReportServiceImpl(FutureTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionSummary> buildClientTransactionSummaries() {
        final List<FutureTransaction> futureTransactionList = repository.findAll();
        final List<TransactionSummary> transactionSummaryList = new ArrayList<>();

        // Grouping the list by Transaction Date and the json/ csv can be fetched day-wise if required. For now, all the transactions are processed alike.
        final Map<Date, List<FutureTransaction>> dayMap = futureTransactionList.stream().collect(Collectors.groupingBy(FutureTransaction::getTransactionDate));

        dayMap.forEach((tnDate, transactionList) -> transactionList.forEach(transaction -> {
            transactionSummaryList.add(buildTransactionSummaryDetail(transaction));
        }));

        return transactionSummaryList;
    }

    private TransactionSummary buildTransactionSummaryDetail(final FutureTransaction transaction) {
        // For grouping different attributes to form a CSV/ JSON field, (:) is being used as a delimiter
        final String clientInformation = transaction.getClientType() + COLON_DELIMITER + transaction.getClientNumber() + COLON_DELIMITER + transaction.getAccountNumber()
                + COLON_DELIMITER + transaction.getSubAccountNumber();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String expirationDateDisplayValue = sdf.format(transaction.getExpirationDate());
        final String productInformation = transaction.getExchangeCode() + COLON_DELIMITER + transaction.getProductGroupCode() + COLON_DELIMITER + transaction.getSymbol()
                + COLON_DELIMITER + expirationDateDisplayValue;
        final String totalTransactionAmount = DOLLAR_SYMBOL + (transaction.getQuantityLong() - transaction.getQuantityShort());

        return (new TransactionSummary(clientInformation, productInformation, totalTransactionAmount));
    }

}
