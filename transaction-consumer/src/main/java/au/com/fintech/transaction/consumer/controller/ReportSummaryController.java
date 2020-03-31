package au.com.fintech.transaction.consumer.controller;

import au.com.fintech.transaction.consumer.model.TransactionSummary;
import au.com.fintech.transaction.consumer.service.ReportCsvHelper;
import au.com.fintech.transaction.consumer.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportSummaryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportSummaryController.class);

    private ReportService service;

    @Autowired
    public ReportSummaryController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/json")
    public List<TransactionSummary> generateReportinJson() {
        LOGGER.info("Starting to generate Transaction Summary JSON");
        final List<TransactionSummary> transactionSummaryList = service.buildClientTransactionSummaries();
        LOGGER.info("Report generation completed");
        return transactionSummaryList;
    }

    @GetMapping("/downloadCsv")
    public void downloadReportAsCsv(final HttpServletResponse response) {
        LOGGER.info("Starting to write to Transaction Summary CSV");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=Output.csv");

        List<TransactionSummary> transactionSummaryList = service.buildClientTransactionSummaries();

        try {
            ReportCsvHelper.writeTransactionSummaryToCsv(response.getWriter(), transactionSummaryList);
        } catch (IOException e) {
            LOGGER.error("Exception occurred while writing Transaction Summary List to csv", e);
        }
    }
}
