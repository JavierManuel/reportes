package com.syt.creditos.reportes.controller;

import com.syt.creditos.reportes.service.JasperReportsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

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
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/boletaPago.jrxml");
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
            item.put("mora", (params.get("mora") != null ? params.get("mora").toString() : "0"));
            item.put("cuotaSeguro", params.get("cuotaSeguro").toString());
            item.put("boleta", params.get("numeroBoleta").toString());
            item.put("usuario", params.get("idUsuario") != null ? params.get("idUsuario").toString() : "-");
            item.put("prestamo", params.get("idPrestamo").toString());
            item.put("montoExtra", params.get("montoExtraCapital").toString());
            item.put("cliente", params.get("cliente") != null ? params.get("cliente").toString() : "-");
            item.put("saldoActual", params.get("saldoActual").toString());
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
 @RequestMapping(value = "plan", method = RequestMethod.POST)
 public void reportPlan(@RequestBody Map<String,Object> params, HttpServletResponse response) throws Exception {
     //response.setContentType("application/pdf");
     JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(/*productService.*/plan(params));
     InputStream inputStream = this.getClass().getResourceAsStream("/reports/cotizacion.jrxml");
     JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

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

     response.setContentType("application/x-pdf");
     response.setHeader("Content-disposition", "inline; filename=App_report_en.pdf");

     final OutputStream outStream = response.getOutputStream();
     JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

 }

    public List<Map<String, Object>> plan(Map<String,Object> params) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //for (Product product : productRepository.findAll()) {
        System.out.println(params.get("detalle"));
        Object al1 = (Object) params.get("detalle");
        System.out.println(al1);
        /*for (int i = 0; i < al1.size(); i++) {
            Object[] objects = (Object[]) al1.get(i);
System.out.println(objects[1]);
        }*/
        ArrayList<Objects> det = (ArrayList<Objects>) params.get("detalle");
/*
        for (Integer i = 0; i < det.size(); i++) {
            Object[] objects = (Object[]) prestamoObject.get(i);
            PrestamoDetalleDTO dto = new PrestamoDetalleDTO();
            dto.setFechaDePago(objects[11]);
            dto.setFechaSistemaEnQuePago(objects[12]);
            dto.setMontoPagado(objects[13]);
            dto.setMoraPagada(objects[14]);
            dto.setAbonoCapital(objects[15]);
            dto.setInteres(objects[16]);
            dto.setCuotaSeguro(objects[17]);
            dto.setMontoExtraCapital(objects[18]);
            System.out.println(objects[19]);
            dto.setIdDetallePago(Long.parseLong(objects[19].toString()));
            dto.setIdUsuario(Integer.parseInt(objects[20].toString()));
            dto.setSaldoActual(objects[21]);
            dtoArray.add(dto);
        }
*/
        System.out.println(det);
        List temp = new ArrayList();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("asesor", params.get("asesor").toString());
        item.put("credito", params.get("credito").toString());
        item.put("detalle", params.get("detalle"));
        item.put("interes", params.get("interes").toString());
        item.put("monto", (params.get("monto") != null ? params.get("monto").toString() : "0"));
        item.put("nombreCompleto", (params.get("nombreCompleto") != null ? params.get("nombreCompleto").toString() : "- - -"));
        item.put("plazo", params.get("plazo").toString());
        item.put("seguro", params.get("seguro").toString());
        item.put("totalCapital", params.get("totalCapital").toString());
        item.put("totalInteres", params.get("totalInteres").toString());
        item.put("totalCuota", params.get("totalCuota").toString());
        item.put("totalSeguro", params.get("totalSeguro").toString());
        result.add(item);
        //}
        return result;
    }

    @RequestMapping(value = "estado", method = RequestMethod.POST)
    public void reportEstadoCuenta(@RequestBody Map<String,Object> params, HttpServletResponse response) throws Exception {
        //response.setContentType("application/pdf");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(estado(params));
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/estadoCuenta.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

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

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=App_report_en.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

    }

    public List<Map<String, Object>> estado(Map<String,Object> params) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Object al1 = (Object) params.get("detalle");
        ArrayList<Objects> det = (ArrayList<Objects>) params.get("detalle");

        System.out.println(det);
        List temp = new ArrayList();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("asesor", params.get("asesor").toString());
        item.put("credito", params.get("credito").toString());
        item.put("detalle", params.get("detalle"));
        item.put("interes", params.get("interes").toString());
        item.put("monto", (params.get("monto") != null ? params.get("monto").toString() : "0"));
        item.put("nombreCompleto", (params.get("nombreCompleto") != null ? params.get("nombreCompleto").toString() : "- - -"));
        item.put("plazo", params.get("plazo").toString());
        item.put("seguro", params.get("seguro").toString());
        item.put("totalCapital", params.get("totalCapital").toString());
        item.put("totalInteres", params.get("totalInteres").toString());
        item.put("totalCuota", params.get("totalCuota").toString());
        item.put("totalSeguro", params.get("totalSeguro").toString());
        item.put("saldo", params.get("saldo").toString());
        item.put("estado", params.get("estado").toString());
        item.put("totalMora", params.get("totalMora").toString());
        item.put("totalExtra", params.get("totalExtra").toString());
        item.put("logo", this.getClass().getResourceAsStream("/reports/credi.png"));
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/reports/credi.png"));
            item.put("logo", image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.add(item);
        //}
        return result;
    }

}
