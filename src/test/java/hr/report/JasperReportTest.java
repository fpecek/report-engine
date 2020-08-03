package hr.report;

import hr.config.IntegrationTestConfiguration;
import hr.dto.ClientDto;
import hr.helper.MockDataHelper;
import hr.report.enums.ReportFileType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Class for testing jasper reports.
 *
 * @author frano.pecek
 */
@RunWith(SpringRunner.class)
@IntegrationTestConfiguration
public class JasperReportTest {

    @Autowired
    private ReportEngine reportEngine;

    /**
     * Test PDF report.
     */
    @Test
    public void generatePdfReport() {
        final List<ClientDto> clientDtos = MockDataHelper.mockClientDtoData();

        final ByteArrayOutputStream byteArrayOutputStream = reportEngine.
                generateReport(ReportFileType.PDF, clientDtos, "classpath:jasper/test-report.jasper");

        assertNotNull(byteArrayOutputStream);
    }

}
