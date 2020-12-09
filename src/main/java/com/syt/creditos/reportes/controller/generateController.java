package com.syt.creditos.reportes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syt.creditos.reportes.dto.*;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
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
        Double totalCapital = 0.0;
        Double totalInteres = 0.0;
        Double totalSeguro= 0.0;
        Double totalMora = 0.0;
        Double totalExtra = 0.0;
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
                    totalCapital = totalCapital + Double.parseDouble(list.get(3).toString());
                    if(list.get(4) == null){
                        dto.setInteres(0.00);
                    }else{
                        dto.setInteres(Double.parseDouble(list.get(4).toString()));
                        totalInteres = totalInteres + Double.parseDouble(list.get(4).toString());
                    }
                    dto.setCuotaSeguro(Double.parseDouble(list.get(5).toString()));
                    totalSeguro = totalSeguro + Double.parseDouble(list.get(5).toString());
                    if(list.get(6) == null){
                        dto.setTotal(0.00);
                    }else{
                        dto.setTotal(Double.parseDouble(list.get(6).toString()));
                        totalMora = totalMora + Double.parseDouble(list.get(6).toString());
                    }
                    if(list.get(7) == null){
                        dto.setPagoExtraCapital(0.00);
                    }else{
                        dto.setPagoExtraCapital(Double.parseDouble(list.get(7).toString()));
                        totalExtra = totalExtra + Double.parseDouble(list.get(7).toString());
                    }

                    SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                    Date ts = input.parse(list.get(8).toString());
                    dto.setFechaSistema(output.format(ts));

                    todo.add(dto);
                }
                if(todo != null && todo.size() > 0) {
                    //ObjectMapper oMapper = new ObjectMapper();
                    Map<String, Object> mapR = new HashMap<String, Object>();
                    mapR.put("totalCapital", totalCapital);
                    mapR.put("totalInteres", totalInteres);
                    mapR.put("totalSeguro", totalSeguro);
                    mapR.put("totalMora", totalMora);
                    mapR.put("totalExtra", totalExtra);

                    mapR.put("dto", todo);
                    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                    try {
                        BufferedImage image = ImageIO.read(getClass().getResource("/reports/credi.png"));
                        mapR.put("logo", image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    result.add(mapR);
                    InputStream inputStream = this.getClass().getResourceAsStream("/reports/ReportePagos.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(result));
                    data = JasperExportManager.exportReportToPdf(jasperPrint);
                }
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }



    @RequestMapping(value = "creditosCartera", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReportecreditosCartera(@Valid @RequestBody Map<String, Object> no) {
        byte[] data = null;
        try {

            Map<String, Object> detallePago = pagoFeing.generarReporteCreditosCartera(no);

            System.out.println(detallePago.get("encabezado"));

//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("pago", Double.parseDouble(params.get("montoPagado").toString()));

            UsuarioDTO encabezado = new UsuarioDTO();
            Collection<UsuarioDTO> todo = new ArrayList<>();
            if (detallePago != null) {
                ArrayList ob = (ArrayList) detallePago.get("encabezado");


            encabezado.setIdUsuarios((Integer) ob.get(0));
            encabezado.setPrimerNombres(ob.get(1).toString());
            encabezado.setPrimerApellidos(ob.get(2).toString());
            encabezado.setDireccions(ob.get(3).toString());
            encabezado.setEmails(ob.get(4).toString());

            ArrayList<Object> detalleCartera = (ArrayList<Object>) detallePago.get("detalleCartera");
            ArrayList<ReporteCreditosCarteraDTO> reportDto = new ArrayList<>();

            for(Integer i = 0; i < detalleCartera.size(); i++){
//                Object[] objects = (Object[]) detalleCartera.get(i);
                ArrayList objects = (ArrayList) detalleCartera.get(i);
//                ArrayList objects = (ArrayList) detallePago.get("detalleCartera");
                ReporteCreditosCarteraDTO report = new ReporteCreditosCarteraDTO();
                report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                report.setPrimerNombre(objects.get(1).toString());
                report.setPrimerApellido(objects.get(2).toString());
                report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                report.setNombrePlan(objects.get(5).toString());
                report.setGarantia(objects.get(6).toString());
                reportDto.add(report);
            }
            System.out.println("ArrayListReporte: ");
            System.out.println(reportDto);
            encabezado.setReporteCreditosCarteraDTO(reportDto);

        }
            System.out.println(encabezado.getPrimerNombres());


            if(detallePago != null){

                ObjectMapper oMapper = new ObjectMapper();
                Map<String, Object> map = oMapper.convertValue(encabezado, Map.class);
                List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/reports/credi.png"));
                    map.put("logo", image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.add(map);
                System.out.println("result");
                System.out.println(result);
                InputStream inputStream = this.getClass().getResourceAsStream("/reports/RporteCreditosPorCartera.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(result));
                data = JasperExportManager.exportReportToPdf(jasperPrint);
                System.out.println(inputStream);
                System.out.println(jasperReport.getCompileData());
                System.out.println(jasperPrint);
                System.out.println(data);

            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }



    @RequestMapping(value = "interesesCarteraFechas", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporteInteresCarteraFechas(@Valid @RequestBody Map<String, Object> no) {
        byte[] data = null;
        try {

            Map<String, Object> detallePago = pagoFeing.generarReporteInteresCarteraFechas(no);

            System.out.println(detallePago.get("encabezado"));

//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("pago", Double.parseDouble(params.get("montoPagado").toString()));

            UsuarioDTO2 encabezado = new UsuarioDTO2();
            Collection<UsuarioDTO2> todo = new ArrayList<>();

            if (detallePago != null) {
                ArrayList ob = (ArrayList) detallePago.get("encabezado");

                encabezado.setIdUsuarios((Integer) ob.get(0));
                encabezado.setPrimerNombres(ob.get(1).toString());
                encabezado.setPrimerApellidos(ob.get(2).toString());
                encabezado.setDireccions(ob.get(3) != null ? ob.get(3).toString() : "");
                encabezado.setEmails(ob.get(4) != null ? ob.get(4).toString() : "");

                ArrayList<Object> detalleCartera = (ArrayList<Object>) detallePago.get("detalleCartera");
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto1 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto2 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto3 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto4 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto5 = new ArrayList<>();
                Double cont1 = 0.00, cont2 = 0.00, cont3 = 0.00, cont4 = 0.00, cont5 = 0.00;
                for(Integer i = 0; i < detalleCartera.size(); i++){
                    ArrayList objects = (ArrayList) detalleCartera.get(i);
                    Boolean estadoInteres = (Boolean) objects.get(8);
                    Integer idEstado = Integer.parseInt(objects.get(9).toString());
                    System.out.println("-----vuelta----- " + i);
                    System.out.println(objects.get(0).toString());
                    System.out.println(estadoInteres);
                    System.out.println(idEstado);
                    System.out.println(objects.get(7).toString());
                    // 0 y 1
                    if (!estadoInteres && idEstado == 1 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto1.add(report);
                        cont1 += Double.parseDouble(objects.get(7).toString());
                        encabezado.setTotal1(cont1);
                        encabezado.setReporteInteresesCarteraFechasDTO1(reportDto1);
                    }
                        // 0 y 2
                    if (!estadoInteres && idEstado == 2 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto2.add(report);
                        cont2 += Double.parseDouble(objects.get(7).toString());
                        if(cont2 == null){
                            encabezado.setTotal2(0.00);
                        } else {
                            encabezado.setTotal2(cont2);
                        }

                        encabezado.setReporteInteresesCarteraFechasDTO2(reportDto2);
                    }
                    // 1 y 2
                    if (estadoInteres && idEstado == 2 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto3.add(report);
                        cont3 += Double.parseDouble(objects.get(7).toString());
                        if(cont3 == null){
                            encabezado.setTotal3(0.00);
                        } else {
                            encabezado.setTotal3(cont3);
                        }
                        encabezado.setReporteInteresesCarteraFechasDTO3(reportDto3);
                    }
                    // 0 y 3
                    if (!estadoInteres && idEstado == 3 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto4.add(report);
                        cont4 += Double.parseDouble(objects.get(7).toString());
                        if(cont4 == null){
                            encabezado.setTotal4(0.00);
                        } else {
                            encabezado.setTotal4(cont4);
                        }

                        encabezado.setReporteInteresesCarteraFechasDTO4(reportDto4);
                    }
                    // 1 y 3
                    if (estadoInteres && idEstado == 3 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto5.add(report);
                        cont5 += Double.parseDouble(objects.get(7).toString());
                        if(cont5 == null){
                            encabezado.setTotal5(0.00);
                        } else {
                            encabezado.setTotal5(cont5);
                        }
                        encabezado.setReporteInteresesCarteraFechasDTO5(reportDto5);
                    }
                    }
            }
            System.out.println("PARA VER SI SIRVE EL REPORTE INTERES");
            System.out.println(encabezado.getPrimerNombres());
            System.out.println(encabezado.getReporteInteresesCarteraFechasDTO1().size());

            if(detallePago != null){

                ObjectMapper oMapper = new ObjectMapper();
                Map<String, Object> map = oMapper.convertValue(encabezado, Map.class);
                List<Map<String, Object>> result = new ArrayList<>();
                result.add(map);
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/reports/credi.png"));
                    map.put("logo", image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("result");
                System.out.println(result);
                InputStream inputStream = this.getClass().getResourceAsStream("/reports/ReporteInteresCarteraFechas.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(result));
                data = JasperExportManager.exportReportToPdf(jasperPrint);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    public  List<Map<String, Object>> resultado;


    @RequestMapping(value = "reporteMorasDias", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)

    public ResponseEntity<byte[]> reporteMorasDias() {

        byte[] data = null;
        try {

            Map<String, Object> detallePago = pagoFeing.reporteMorasDias();
            System.out.println(detallePago.get("detalle"));

//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("pago", Double.parseDouble(params.get("montoPagado").toString()));

            ReporteMorasDTO encabezado = new ReporteMorasDTO();
            Collection<UsuarioDTO2> todo = new ArrayList<>();

            if (detallePago != null) {

                ArrayList<Object> detalle = (ArrayList<Object>) detallePago.get("detalle");
                ArrayList<ReporteMorasUnoDTO> reportDto1 = new ArrayList<>();
                ArrayList<ReporteMorasDosGraciaDTO> reportDto2 = new ArrayList<>();

                Double cont1 = 0.00, cont2 = 0.00;
                Date date = new Date();

                for(Integer i = 0; i < detalle.size(); i++){
                    ArrayList objects = (ArrayList) detalle.get(i);

                    Date fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(objects.get(5).toString());
                    Long dias = ((date.getTime() - fechaIni.getTime())/86400000);
                    System.out.println(dias);

                    // en mora ya contando
                    if (dias > 3){
                        int mes = fechaIni.getMonth();
                        int restaMes = (date.getMonth()+1)-(mes+1);
                        ReporteMorasUnoDTO report = new ReporteMorasUnoDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setMoraAsignada(Double.parseDouble(objects.get(4).toString()));
                        report.setCuotasAtrasadas((restaMes +1));
                        report.setDiasAtraso(dias);
                        report.setTotalMora(dias*Double.parseDouble(objects.get(4).toString()));
                        reportDto1.add(report);
                        cont1 += report.getTotalMora();
                        if(cont1 == null){
                            encabezado.setTotal1(0.00);
                        } else {
                            encabezado.setTotal1(cont1);
                        }
                        encabezado.setReporteMorasUnoDTO(reportDto1);
                    } else {

                        ReporteMorasDosGraciaDTO report = new ReporteMorasDosGraciaDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setMoraAsignada(Double.parseDouble(objects.get(4).toString()));
                        report.setDiasGracia(dias);
                        reportDto2.add(report);
                        encabezado.setReporteMorasDosGraciaDTO(reportDto2);

                    }


                }
            }


            if(detallePago != null){



                ObjectMapper oMapper = new ObjectMapper();
                Map<String, Object> map = oMapper.convertValue(encabezado, Map.class);
                List<Map<String, Object>> result = new ArrayList<>();
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/reports/credi.png"));
                    map.put("logo", image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.add(map);
                System.out.println("IMAGEN QUE VA...");
                System.out.println(map.get("logo"));
                this.resultado = result;
                System.out.println("result");
                System.out.println(result);
                InputStream inputStream = this.getClass().getResourceAsStream("/reports/ReporteMorasPorDia.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(result));
                data = JasperExportManager.exportReportToPdf(jasperPrint);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }


    @RequestMapping(value = "seguroCarteraFechas", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporteSeguroCarteraFechas(@Valid @RequestBody Map<String, Object> no) {
        byte[] data = null;
        try {

            Map<String, Object> detallePago = pagoFeing.generarReporteSeguroCarteraFechas(no);

            System.out.println(detallePago.get("encabezado"));

//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("pago", Double.parseDouble(params.get("montoPagado").toString()));

            UsuarioDTO2 encabezado = new UsuarioDTO2();
            Collection<UsuarioDTO2> todo = new ArrayList<>();

            if (detallePago != null) {
                ArrayList ob = (ArrayList) detallePago.get("encabezado");

                encabezado.setIdUsuarios((Integer) ob.get(0));
                encabezado.setPrimerNombres(ob.get(1).toString());
                encabezado.setPrimerApellidos(ob.get(2).toString());
                encabezado.setDireccions(ob.get(3) != null ? ob.get(3).toString() : "");
                encabezado.setEmails(ob.get(4) != null ? ob.get(4).toString() : "");

                ArrayList<Object> detalleCartera = (ArrayList<Object>) detallePago.get("detalleCartera");
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto1 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto2 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto3 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto4 = new ArrayList<>();
                ArrayList<ReporteInteresesCarteraFechasDTO> reportDto5 = new ArrayList<>();
                Double cont1 = 0.00, cont2 = 0.00, cont3 = 0.00, cont4 = 0.00, cont5 = 0.00;
                for(Integer i = 0; i < detalleCartera.size(); i++){
                    ArrayList objects = (ArrayList) detalleCartera.get(i);
                    Boolean estadoInteres = (Boolean) objects.get(8);
                    Integer idEstado = Integer.parseInt(objects.get(9).toString());
                    System.out.println("-----vuelta----- " + i);
                    System.out.println(objects.get(0).toString());
                    System.out.println(estadoInteres);
                    System.out.println(idEstado);
                    System.out.println(objects.get(7).toString());
                    // 0 y 1
                    if (!estadoInteres && idEstado == 1 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto1.add(report);
                        cont1 += Double.parseDouble(objects.get(7).toString());
                        encabezado.setTotal1(cont1);
                        encabezado.setReporteInteresesCarteraFechasDTO1(reportDto1);
                    }
                    // 0 y 2
                    if (!estadoInteres && idEstado == 2 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto2.add(report);
                        cont2 += Double.parseDouble(objects.get(7).toString());
                        if(cont2 == null){
                            encabezado.setTotal2(0.00);
                        } else {
                            encabezado.setTotal2(cont2);
                        }

                        encabezado.setReporteInteresesCarteraFechasDTO2(reportDto2);
                    }
                    // 1 y 2
                    if (estadoInteres && idEstado == 2 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto3.add(report);
                        cont3 += Double.parseDouble(objects.get(7).toString());
                        if(cont3 == null){
                            encabezado.setTotal3(0.00);
                        } else {
                            encabezado.setTotal3(cont3);
                        }
                        encabezado.setReporteInteresesCarteraFechasDTO3(reportDto3);
                    }
                    // 0 y 3
                    if (!estadoInteres && idEstado == 3 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto4.add(report);
                        cont4 += Double.parseDouble(objects.get(7).toString());
                        if(cont4 == null){
                            encabezado.setTotal4(0.00);
                        } else {
                            encabezado.setTotal4(cont4);
                        }

                        encabezado.setReporteInteresesCarteraFechasDTO4(reportDto4);
                    }
                    // 1 y 3
                    if (estadoInteres && idEstado == 3 ){
                        ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                        report.setIdPrestamo(Long.parseLong(objects.get(0).toString()));
                        report.setPrimerNombre(objects.get(1).toString());
                        report.setPrimerApellido(objects.get(2).toString());
                        report.setMontoAsignado(Double.parseDouble(objects.get(3).toString()));
                        report.setPlazoMeses( Integer.parseInt(objects.get(4).toString()));
                        report.setNombrePlan(objects.get(5).toString());
                        report.setGarantia(objects.get(6).toString());
                        report.setSumaTotal(Double.parseDouble(objects.get(7).toString()));
                        report.setEstadoInteres((Boolean) objects.get(8));
                        report.setEstadoPerdon(Integer.parseInt(objects.get(9).toString()));
                        report.setNombrePerdon(objects.get(10).toString());
                        reportDto5.add(report);
                        cont5 += Double.parseDouble(objects.get(7).toString());
                        if(cont5 == null){
                            encabezado.setTotal5(0.00);
                        } else {
                            encabezado.setTotal5(cont5);
                        }
                        encabezado.setReporteInteresesCarteraFechasDTO5(reportDto5);
                    }
                }
            }
            System.out.println("PARA VER SI SIRVE EL REPORTE INTERES");
            System.out.println(encabezado.getPrimerNombres());
            //System.out.println(encabezado.getReporteInteresesCarteraFechasDTO1().size());

            if(detallePago != null){

                ObjectMapper oMapper = new ObjectMapper();
                Map<String, Object> map = oMapper.convertValue(encabezado, Map.class);
                List<Map<String, Object>> result = new ArrayList<>();
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/reports/credi.png"));
                    map.put("logo", image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.add(map);
                System.out.println("result");
                System.out.println(result);
                InputStream inputStream = this.getClass().getResourceAsStream("/reports/ReporteSeguroCarteraFechas.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(result));
                data = JasperExportManager.exportReportToPdf(jasperPrint);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }



}
