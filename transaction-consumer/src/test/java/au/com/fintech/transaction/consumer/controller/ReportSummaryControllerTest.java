package au.com.fintech.transaction.consumer.controller;

import au.com.fintech.transaction.consumer.model.TransactionSummary;
import au.com.fintech.transaction.consumer.service.ReportCsvHelper;
import au.com.fintech.transaction.consumer.service.ReportService;
import com.opencsv.CSVWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import static au.com.fintech.transaction.consumer.service.ReportCsvHelper.CLIENT_INFORMATION;
import static au.com.fintech.transaction.consumer.service.ReportCsvHelper.PRODUCT_INFORMATION;
import static au.com.fintech.transaction.consumer.service.ReportCsvHelper.TOTAL_TRANSACTION_AMOUNT;
import static au.com.fintech.transaction.consumer.utils.ReflectionHelper.setStaticFinalField;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ReportCsvHelper.class})
public class ReportSummaryControllerTest {

    @Mock private Logger logger;

    @Mock private Logger csvLogger;

    @Mock
    private ReportService service;

    private ReportSummaryController controller;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        controller = new ReportSummaryController(service);
        setStaticFinalField(controller.getClass(), "LOGGER", logger);
        setStaticFinalField(ReportCsvHelper.class, "LOGGER", csvLogger);
    }

    @Test
    public void shouldGenerateReportInJsonForEmptyScenario() {
        given(service.buildClientTransactionSummaries()).willReturn(Collections.emptyList());
        final List<TransactionSummary> summaryList = controller.generateReportinJson();
        verify(logger).info("Starting to generate Transaction Summary JSON");
        verify(logger).info("Report generation completed");
        assertTrue(summaryList.isEmpty());
    }

    @Test
    public void shouldGenerateReportInJson() {
        final TransactionSummary summary1 = new TransactionSummary("CL 1", "PR 1", "$10");
        final TransactionSummary summary2 = new TransactionSummary("CL 2", "PR 2", "$20");
        given(service.buildClientTransactionSummaries()).willReturn(asList(summary1, summary2));
        final List<TransactionSummary> summaryList = controller.generateReportinJson();
        verify(logger).info("Starting to generate Transaction Summary JSON");
        verify(logger).info("Report generation completed");
        assertEquals(2, summaryList.size());
        assertEquals(summary1, summaryList.get(0));
        assertEquals(summary2, summaryList.get(1));
    }

    @Test
    public void downloadReportAsCsvAndHandleException() {
        final TransactionSummary summary1 = new TransactionSummary("CL 1", "PR 1", "$10");
        final TransactionSummary summary2 = new TransactionSummary("CL 2", "PR 2", "$20");
        given(service.buildClientTransactionSummaries()).willReturn(asList(summary1, summary2));

        final HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        controller.downloadReportAsCsv(httpServletResponse);
        verify(logger).info("Starting to write to Transaction Summary CSV");
        verify(httpServletResponse).setContentType("text/csv");
        verify(httpServletResponse).setHeader("Content-Disposition", "attachment; filename=Output.csv");
        verify(csvLogger).error(eq("Error in writing Transaction Summary to CSV"), any(Exception.class));
    }

    @Test
    public void downloadReportAsCsv() throws Exception {

        final TransactionSummary summary1 = new TransactionSummary("CL 1", "PR 1", "$10");
        final TransactionSummary summary2 = new TransactionSummary("CL 2", "PR 2", "$20");
        given(service.buildClientTransactionSummaries()).willReturn(asList(summary1, summary2));

        final HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        final PrintWriter printWriter = mock(PrintWriter.class);
        given(httpServletResponse.getWriter()).willReturn(printWriter);
        final CSVWriter csvWriter = mock(CSVWriter.class);
        final ArgumentCaptor<String[]> contentCaptor = ArgumentCaptor.forClass(String[].class);
        spy(ReportCsvHelper.class);
        when(ReportCsvHelper.getCsvWriter(any(PrintWriter.class))).thenReturn(csvWriter);
        controller.downloadReportAsCsv(httpServletResponse);

        verify(logger).info("Starting to write to Transaction Summary CSV");
        verify(httpServletResponse).setContentType("text/csv");
        verify(httpServletResponse).setHeader("Content-Disposition", "attachment; filename=Output.csv");
        verify(csvWriter, times(3)).writeNext(contentCaptor.capture());

        final List<String[]> capturedValue = contentCaptor.getAllValues();
        final String[] expectedHeaderValue = {CLIENT_INFORMATION, PRODUCT_INFORMATION, TOTAL_TRANSACTION_AMOUNT};
        final String[] expectedContent1 = {"CL 1", "PR 1", "$10"};
        final String[] expectedContent2 = {"CL 2", "PR 2", "$20"};
        assertArrayEquals(expectedHeaderValue, capturedValue.get(0));
        assertArrayEquals(expectedContent1, capturedValue.get(1));
        assertArrayEquals(expectedContent2, capturedValue.get(2));
        verify(csvLogger).info("Finished writing to Output.CSV");
    }
}