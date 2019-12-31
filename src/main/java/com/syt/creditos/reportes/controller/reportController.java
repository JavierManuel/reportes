package com.syt.creditos.reportes.controller;

import com.syt.creditos.reportes.service.JasperReportsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("product")
public class reportController {

    @Autowired
    private JasperReportsService reportService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "report", method = RequestMethod.POST)
    public void report(@RequestBody Map<String,Object> params, HttpServletResponse response) throws Exception {
        //response.setContentType("application/pdf");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(/*productService.*/report(params));
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/constanciaPago.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        /*
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();*/


        JRPdfExporter exporter2 = new JRPdfExporter();

        exporter2.setExporterInput(new SimpleExporterInput(jasperPrint));
        //exporter2.setExporterOutput(new SimpleOutputStreamExporterOutput("employeeReport.pdf"));

        SimplePdfReportConfiguration reportConfig
                = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig
                = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("syt");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter2.setConfiguration(reportConfig);
        exporter2.setConfiguration(exportConfig);

        //exporter2.exportReport();
        //exporter2.getExporterOutput();

        //response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
/*
        final OutputStream outStream = exporter2.getExporterOutput().getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
*/
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=App_report_en.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);


/*
        InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/HelloWorld1.jasper");
        Map<String,Object> params = new HashMap<>();
        JasperReport jasperReport2 = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, params, new JREmptyDataSource());

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);*/

    }

    public List<Map<String, Object>> report(Map<String,Object> params) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //for (Product product : productRepository.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("pago", Double.parseDouble(params.get("montoPagado").toString()));
            item.put("fecha", params.get("fechaDePago").toString());
            item.put("abonoCapital", params.get("abonoCapital").toString());
            item.put("interes", params.get("interes").toString());
            item.put("mora", (params.get("mora").toString() != null ? params.get("mora").toString() : '0'));
            item.put("cuotaSeguro", params.get("cuotaSeguro").toString());
            item.put("boleta", params.get("numeroBoleta").toString());
            item.put("usuario", params.get("idUsuario").toString() != null ? params.get("idUsuario").toString() : '-');
            item.put("prestamo", params.get("idPrestamo").toString());
            item.put("montoExtra", params.get("montoExtraCapital").toString());
            result.add(item);
        //}
        return result;
    }

    @RequestMapping("/{username}")
    public ResponseEntity<List<Object>> report(@PathVariable(required = false) String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("pagoTotal", username);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(/*productService.*/report(params));
        //InputStream inputStream = this.getClass().getResourceAsStream("/reports/constanciaPago.jrxml");
        List<Object> bytes = reportService.generateInlineHtmlReport("/reports/constanciaPago.jrxml", params);
        return ResponseEntity
                .ok()
                // Specify content type as PDF
                .header("Content-Type", "application/pdf; charset=UTF-8")
                // Tell browser to display PDF if it can
                .header("Content-Disposition", "inline; filename=\"" + username + ".pdf\"")
                .body(bytes);
    }

 /*   @RequestMapping(value = "report1", method = RequestMethod.GET)
    public ModelAndView report1(HttpServletResponse response) throws Exception {

        JasperReportsPdfView view ;

    }*/


}
