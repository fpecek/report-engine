package hr.report.controller;

import hr.commons.search.model.SearchCriteria;
import hr.dto.ClientListItemDto;
import hr.entity.janus.untyped.Client;
import hr.mapper.ClientMapper;
import hr.report.ReportEngine;
import hr.report.dto.ReportDto;
import hr.service.client.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Rest controller used for generating reports.
 *
 * @author frano.pecek
 */
@RestController
@RequestMapping(ReportController.BASE_URL)
public class ReportController {

    /**
     * Base URL for report controller.
     */
    public static final String BASE_URL = "/report";

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportEngine reportEngine;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

	// WARNING: demo implementation, will be implemented later
    /**
     * Endpoint for generating report.
     *
     * @param reportDto {@link ReportDto} object identifier
     * @return byte array stream {@link ByteArrayOutputStream}
     */
    @GetMapping(consumes = "application/report.v1+json")
    public byte[] createReport(@RequestBody @Valid final ReportDto reportDto) {
        LOGGER.info("Started generating report with descriptor: {} ...", reportDto);

        final List<Client> clients = clientService.findAll(new SearchCriteria());

        //TODO make template path constant
        final ByteArrayOutputStream reportByteArray = reportEngine.generateReport(reportDto.getFileType(), clients, "classpath:jasper/display-all-clients-report.jasper");

        return reportByteArray.toByteArray();
    }
}
