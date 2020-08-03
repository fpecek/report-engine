package hr.report.processor;

import hr.config.IntegrationTestConfiguration;
import hr.dto.ClientDto;
import hr.helper.MockDataHelper;
import hr.report.enums.ReportFileType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Testing XML exporter processor working as expected.
 *
 * @author frano.pecek
 */
@RunWith(SpringRunner.class)
@IntegrationTestConfiguration
public class XmlProcessorTest {

    @Autowired
    private ExportProcessor xmlExportProcessor;

    /**
     * Test XML exporter processor is successfully created XML file.
     *
     * @throws JRException jasper exception
     * @throws IOException exception creating file
     */
    @Test
    public void testXml() throws JRException, IOException {
        final List<ClientDto> clientDtos = MockDataHelper.mockClientDtoData();

        final JasperReport report = (JasperReport) JRLoader.loadObject(JRLoader.getLocationInputStream("jasper/test-report.jasper"));
        final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientDtos);

        final ByteArrayOutputStream reportStream = xmlExportProcessor.exportReport(dataSource, report);

        final File reportFile = File.createTempFile("report_", ".xml");
        try (OutputStream fos = Files.newOutputStream(Paths.get(reportFile.getPath()))) {
            fos.write(reportStream.toByteArray());
        }

        assertThat(reportFile.exists(), is(true));
        reportFile.delete();
    }

    /**
     * Check that exporter processor type is not changed.
     */
    @Test
    public void testGetType() {
        assertEquals(ReportFileType.XML, xmlExportProcessor.getType());
    }

}
