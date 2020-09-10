package com.syt.creditos.reportes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syt.creditos.reportes.dto.ReportePagosDTO;
import com.syt.creditos.reportes.feingService.pago;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("service")
public class generateController {

    @Autowired
    private pago pagoFeing;

    @RequestMapping(value = "report", method = RequestMethod.POST)
    public byte[] report(@RequestBody Map<String,Object> params/*HttpServletResponse response*/) throws Exception {

        ObjectMapper oMapper = new ObjectMapper();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(params.get("bos"));
        oos.flush();
        //byte [] data = bos.toByteArray();

            ByteArrayOutputStream outputStream = bos;

        Object datosObj = params.get("mapper");
        Map<String,Object> paramsBody = oMapper.convertValue(datosObj, Map.class);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportMapper(paramsBody));
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/constanciaPago.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);


        JRPdfExporter exporter2 = new JRPdfExporter();

        exporter2.setExporterInput(new SimpleExporterInput(jasperPrint));

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

        //response.setContentType("application/x-pdf");
        //response.setHeader("Content-disposition", "inline; filename=App_report_en.pdf");

        //final OutputStream outStream = response.getOutputStream();
        //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream.toByteArray();
    }

    public List<Map<String, Object>> reportMapper(Map<String,Object> params) {
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
        result.add(item);
        //}
        return result;
    }

    /*@RequestMapping(value = "pagos1", method = RequestMethod.POST)
    public byte[] generarReporteFechas(@RequestBody ArrayList<Object> params) {
        byte[] data = null;
        try {
            //File file = new ClassPathResource("/reports/ReportePagos.jasper").getFile();
            //InputStream inputStream = this.getClass().getResourceAsStream("/reports/constanciaPago.jrxml");

            //System.out.println(file);
            System.out.println("service reporteporfechas 1: ");
            System.out.println("FECHAS JUNTAS: ");
            ArrayList<Object> detallePago = params;
            ArrayList<ReportePagosDTO> todo = new ArrayList<>();

            if(detallePago.size() > 0){

                for (Integer i = 0; i < detallePago.size(); i++){
                    System.out.println("asdf: " + detallePago.get(i));
                    ArrayList list = (ArrayList) detallePago.get(i);
                    ReportePagosDTO dto = new ReportePagosDTO();
                    dto.setIdPrestamo(Long.parseLong(list.get(0).toString()));
                    dto.setPrimerNombre(list.get(1).toString());
                    dto.setPrimerApellido(list.get(2).toString());
                    dto.setAbonoCapital(Double.parseDouble(list.get(3).toString()));
                    if(list.get(4) == null){
                        dto.setInteres(0.00);
                    }else{
                        dto.setInteres(Double.parseDouble(list.get(4).toString()));
                    }
                    dto.setCuotaSeguro(Double.parseDouble(list.get(5).toString()));
                    if(list.get(6) == null){
                        dto.setTotal(0.00);
                    }else{
                        dto.setTotal(Double.parseDouble(list.get(6).toString()));
                    }
                    if(list.get(7) == null){
                        dto.setPagoExtraCapital(0.00);
                    }else{
                        dto.setPagoExtraCapital(Double.parseDouble(list.get(7).toString()));
                    }
                    System.out.println("hjk: " + list.get(1));
                    /*Object[] objects = (Object[]) detallePago.get(i);
                    ReportePagosDTO dto = new ReportePagosDTO();
                    dto.setIdPrestamo(Long.parseLong(objects[0].toString()));
                    dto.setPrimerNombre(objects[1].toString());
                    dto.setPrimerApellido(objects[2].toString());
                    dto.setAbonoCapital(Double.parseDouble(objects[3].toString()));
                    if(objects[4] == null){
                        dto.setInteres(0.00);
                    }else{
                        dto.setInteres(Double.parseDouble(objects[4].toString()));
                    }
                    dto.setCuotaSeguro(Double.parseDouble(objects[5].toString()));
                    if(objects[6] == null){
                        dto.setTotal(0.00);
                    }else{
                        dto.setTotal(Double.parseDouble(objects[6].toString()));
                    }
                    if(objects[7] == null){
                        dto.setPagoExtraCapital(0.00);
                    }else{
                        dto.setPagoExtraCapital(Double.parseDouble(objects[7].toString()));
                    }*/
   /*                 SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                    //System.out.println(objects[1].toString());
                    //System.out.println(objects[8].toString());
                    Date ts = input.parse(list.get(8).toString());
//                    System.out.println(output.format(objects[8].toString()));
                    dto.setFechaSistema(output.format(ts));


                    todo.add(dto);
                }


                InputStream inputStream = this.getClass().getResourceAsStream("/reports/ReportePagos.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(todo));

                //JasperPrint print = JasperFillManager.fillReport(file.getPath(), null,
                  //      new JRBeanCollectionDataSource(todo));
                System.out.println("service reporteporfechas: ");
//                System.out.println(service.reportePagosPorFechas());
                data = JasperExportManager.exportReportToPdf(jasperPrint);
                System.out.println();
                System.out.println(todo);
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }*/

    @RequestMapping(value = "pagos", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReportePagosFechas(@Valid @RequestBody Map<String, Object> map) {
        byte[] data = null;
        try {

            ArrayList<Object> detallePago = pagoFeing.generarReporte(map);
            ArrayList<ReportePagosDTO> todo = new ArrayList<>();

            if(detallePago.size() > 0){

                for (Integer i = 0; i < detallePago.size(); i++){
                    ArrayList list = (ArrayList) detallePago.get(i);
                    ReportePagosDTO dto = new ReportePagosDTO();
                    dto.setIdPrestamo(Long.parseLong(list.get(0).toString()));
                    dto.setPrimerNombre(list.get(1).toString());
                    dto.setPrimerApellido(list.get(2).toString());
                    dto.setAbonoCapital(Double.parseDouble(list.get(3).toString()));
                    if(list.get(4) == null){
                        dto.setInteres(0.00);
                    }else{
                        dto.setInteres(Double.parseDouble(list.get(4).toString()));
                    }
                    dto.setCuotaSeguro(Double.parseDouble(list.get(5).toString()));
                    if(list.get(6) == null){
                        dto.setTotal(0.00);
                    }else{
                        dto.setTotal(Double.parseDouble(list.get(6).toString()));
                    }
                    if(list.get(7) == null){
                        dto.setPagoExtraCapital(0.00);
                    }else{
                        dto.setPagoExtraCapital(Double.parseDouble(list.get(7).toString()));
                    }

                    SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                    Date ts = input.parse(list.get(8).toString());
                    dto.setFechaSistema(output.format(ts));

                    todo.add(dto);
                }

                InputStream inputStream = this.getClass().getResourceAsStream("/reports/ReportePagos.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(todo));
                data = JasperExportManager.exportReportToPdf(jasperPrint);
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

}
