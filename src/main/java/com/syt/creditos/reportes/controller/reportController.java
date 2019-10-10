package com.syt.creditos.reportes.controller;

import com.syt.creditos.reportes.service.JasperReportsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product")
public class reportController {

    @Autowired
    private JasperReportsService reportService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "report", method = RequestMethod.GET)
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(/*productService.*/report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/constanciaPago.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();

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

    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //for (Product product : productRepository.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("pago", 25.00);
            /*item.put("name", product.getName());
            item.put("price", product.getPrice());
            item.put("quantity", product.getQuantity());
            item.put("categoryName", product.getCategoryName());*/
            result.add(item);
        //}
        return result;
    }

    @RequestMapping("/{username}")
    public ResponseEntity<List<Object>> report(@PathVariable(required = false) String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("pagoTotal", username);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(/*productService.*/report());
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
