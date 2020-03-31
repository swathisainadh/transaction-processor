package au.com.fintech.transaction.consumer.service;

import au.com.fintech.transaction.consumer.model.TransactionSummary;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;

public class ReportCsvHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportCsvHelper.class);

    public static final String CLIENT_INFORMATION = "Client_Information";
    public static final String PRODUCT_INFORMATION = "Product_Information";
    public static final String TOTAL_TRANSACTION_AMOUNT = "Total_Transaction_Amount";
    public static final String[] CSV_HEADER = {CLIENT_INFORMATION, PRODUCT_INFORMATION, TOTAL_TRANSACTION_AMOUNT};

    public static void writeTransactionSummaryToCsv(final PrintWriter writer, final List<TransactionSummary> customers) {
        try (CSVWriter csvWriter = getCsvWriter(writer)) {
            csvWriter.writeNext(CSV_HEADER);
            for (final TransactionSummary customer : customers) {
                String[] data = {
                        customer.getClientInformation(),
                        customer.getProductInformation(),
                        customer.getTotalTransactionAmount()
                };

                csvWriter.writeNext(data);
            }
            LOGGER.info("Finished writing to Output.CSV");
        } catch (Exception e) {
            LOGGER.error("Error in writing Transaction Summary to CSV", e);
        }
    }

    public static CSVWriter getCsvWriter(PrintWriter writer) {
        return new CSVWriter(writer,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
    }
}
