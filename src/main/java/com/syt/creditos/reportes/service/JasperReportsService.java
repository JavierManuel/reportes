package com.syt.creditos.reportes.service;

import com.syt.creditos.reportes.interfaces.ReportService;
import com.syt.creditos.reportes.storage.StorageService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.MapHtmlResourceHandler;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JasperReportsService implements ReportService {

    private Logger log = LoggerFactory.getLogger(JasperReportsService.class);

/*
    private StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }*/

    /*
    @Autowired
    private StorageService storageService;

    public JasperReportsService(StorageService storageService) {
        this.storageService = storageService;
    }*/

    /*
     * (non-Javadoc)
     *
     * @see com.juliuskrah.jasper.mail.ReportService#generateHtmlReport(java.lang.String,
     * java.util.Map)
     */
    @Override
    public String generateHtmlReport(String inputFileName, Map<String, Object> params) {
        return generateHtmlReport(inputFileName, params, new JREmptyDataSource());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.juliuskrah.jasper.mail.ReportService#generateHtmlReport(java.lang.String,
     * java.util.Map, net.sf.jasperreports.engine.JRDataSource)
     */
    @Override
    public String generateHtmlReport(String inputFileName, Map<String, Object> params,
                                     JRDataSource dataSource) {
        byte[] bytes = null;
        JasperReport jasperReport = null;
        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
            // Check if a compiled report exists
            InputStream inputStream = this.getClass().getResourceAsStream("/reports/constanciaPago.jrxml");
            if (/*storageService.jasperFileExists(inputFileName)*/true) {
                jasperReport = (JasperReport) JRLoader
                        .loadObject(loadJasperFile(inputFileName));
            }
            // Compile report from source and save
            else {
                String jrxml = loadJrxmlFile(inputFileName);
                log.info("{} loaded. Compiling report", jrxml);
                jasperReport = JasperCompileManager.compileReport(jrxml);
                // Save compiled report. Compiled report is loaded next time
                JRSaver.saveObject(jasperReport,
                        loadJasperFile(inputFileName));
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
                    dataSource);
            Exporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> exporter;
            exporter = new HtmlExporter();

            exporter.setExporterOutput(new SimpleHtmlExporterOutput(byteArray));
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.exportReport();
            bytes = byteArray.toByteArray();
        }
        catch (JRException | IOException e) {
            e.printStackTrace();
            log.error("Encountered error when loading jasper file", e);
        }

        return new String(bytes);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.juliuskrah.jasper.mail.ReportService#generateInlineHtmlReport(java.lang.String,
     * java.util.Map)
     */
    @Override
    public List<Object> generateInlineHtmlReport(String inputFileName,
                                                 Map<String, Object> params) {
        return generateInlineHtmlReport(inputFileName, params, new JREmptyDataSource());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.juliuskrah.jasper.mail.ReportService#generateInlineHtmlReport(java.lang.String,
     * java.util.Map, net.sf.jasperreports.engine.JRDataSource)
     */
    @Override
    public List<Object> generateInlineHtmlReport(String inputFileName,
                                                 Map<String, Object> params, JRDataSource jRDataSource) {
        JasperReport jasperReport = null;
        List<Object> result = new ArrayList<>();

        // Map<byte[], Map<String, byte[]>> result = new HashMap<>();
        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
            if (/*storageService.jasperFileExists(inputFileName)*/true) {
                jasperReport = (JasperReport) JRLoader
                        .loadObject(loadJasperFile(inputFileName));
            }
            else {
                String jrxml = loadJrxmlFile(inputFileName);
                log.debug("{} loaded. Compiling report", jrxml);
                jasperReport = JasperCompileManager.compileReport(jrxml);
                JRSaver.saveObject(jasperReport,
                        loadJasperFile(inputFileName));
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
                    jRDataSource);
            Exporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> exporter;
            exporter = new HtmlExporter();
            Map<String, byte[]> resourcesMap = new HashMap<>();
            SimpleHtmlExporterOutput htmlExporterOutput = new SimpleHtmlExporterOutput(
                    byteArray);
            htmlExporterOutput
                    .setImageHandler(new MapHtmlResourceHandler((resourcesMap)) {
                        @Override
                        public String getResourcePath(String id) {
                            return "cid:" + id;
                        }

                    });

            exporter.setExporterOutput(htmlExporterOutput);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.exportReport();
            String html = new String(byteArray.toByteArray());
            result.add(html);
            result.add(resourcesMap);
        }
        catch (JRException | IOException e) {
            log.error("Encountered error when loading jasper file", e);
        }

        return result;
    }

    /*public boolean jrxmlFileExists(String file) {
        // @formatter:off
        try {
            Path reportFile = Paths.get(properties.getReportLocation().getURI());
            reportFile = reportFile.resolve(file + ".jrxml");
            if (Files.exists(reportFile))
                return true;
        } catch (IOException e) {
            log.error("Error while trying to get file URI", e);
            return false;
        }
        // @formatter:on
        return false;
    }*/

    public String loadJrxmlFile(String file) {
        // @formatter:off
        Path reportFile = Paths.get("/");
        reportFile = reportFile.resolve("reports/constanciaPago.jrxml");
        return reportFile.toString();
        // @formatter:on
    }

    public File loadJasperFile(String file) {
        Path reportFile = Paths.get("/");;
        reportFile = reportFile.resolve("reports/constanciaPago.jasper");
        return reportFile.toFile();
    }

}
