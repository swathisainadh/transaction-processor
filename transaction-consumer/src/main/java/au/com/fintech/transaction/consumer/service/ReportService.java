package au.com.fintech.transaction.consumer.service;

import au.com.fintech.transaction.consumer.model.TransactionSummary;

import java.util.List;

public interface ReportService {

    List<TransactionSummary> buildClientTransactionSummaries();
}
