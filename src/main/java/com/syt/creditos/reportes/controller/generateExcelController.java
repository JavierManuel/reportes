package com.syt.creditos.reportes.controller;

import com.syt.creditos.reportes.dto.ReporteInteresesCarteraFechasDTO;
import com.syt.creditos.reportes.dto.UsuarioDTO2;
import com.syt.creditos.reportes.feingService.pago;
import com.syt.creditos.reportes.service.ExcelReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("excel")
public class generateExcelController {

    @Autowired
    private ExcelReport excelReport;

    @Autowired
    private pago pagoFeing;

    @PostMapping(value = "/pagos")
    public ResponseEntity<InputStreamResource> subVaribleExcel(@Valid @RequestBody Map<String, Object> map) throws IOException {

        ArrayList<Object> detallePago = pagoFeing.generarReporte(map);
        ArrayList<String> array = new ArrayList<>();
        array.add("Crédito");
        array.add("Nombre");
        array.add("Apellido");
        array.add("Abono Capital");
        array.add("Interés");
        array.add("Cuota Seguro");
        array.add("Mora");
        array.add("Pago Extra Capital");
        array.add("Fecha Sistema");

        ByteArrayInputStream in = excelReport.generarExcel(array, detallePago);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=subVariables.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }



    @PostMapping(value = "/creditosCartera")
    public ResponseEntity<InputStreamResource> creditosCartera(@Valid @RequestBody Map<String, Object> map) throws IOException {

        Map<String, Object> detallePago = pagoFeing.generarReporteCreditosCartera(map);
        ArrayList ob = (ArrayList) detallePago.get("detalleCartera");
        ArrayList<String> array = new ArrayList<>();
        array.add("Crédito");
        array.add("Nombre");
        array.add("Apellido");
        array.add("Monto asignado");
        array.add("Plazo");
        array.add("Plan");


        ByteArrayInputStream in = excelReport.generarExcel(array, ob);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=subVariables.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }



    @PostMapping(value = "/reporteMorasDias")
    public ResponseEntity<InputStreamResource> reporteMorasDias() throws IOException {

//        Map<String, Object> detallePago = pagoFeing.generarReporteCreditosCartera(map);
        Map<String, Object> detalle = pagoFeing.reporteMorasDias();
        System.out.println(detalle.get("detalle"));
        ArrayList ob = (ArrayList) detalle.get("detalle");
        ArrayList<String> array = new ArrayList<>();
        array.add("Crédito");
        array.add("Nombre");
        array.add("Apellido");
        array.add("Desembolso");
        array.add("Mora asignada");
        array.add("Dias de atraso");
        array.add("Total mora");
        ArrayList<String> array2 = new ArrayList<>();
        array2.add("Crédito");
        array2.add("Nombre");
        array2.add("Apellido");
        array2.add("Desembolso");
        array2.add("Mora asignada");
        array2.add("Dias de gracia");


        ByteArrayInputStream in = excelReport.generarExcel(array, ob);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=subVariables.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @PostMapping(value = "/reporteIntereses")
    public ResponseEntity<InputStreamResource> reporteIntetereses(@Valid @RequestBody Map<String, Object> map) throws IOException {

        Map<String, Object> detallePago = pagoFeing.generarReporteInteresCarteraFechas(map);
        ArrayList<String> array = new ArrayList<>();
        array.add("Crédito");
        array.add("Nombre");
        array.add("Apellido");
        array.add("Desembolso");
        array.add("Plazo");
        array.add("Plan");
        array.add("Garantía");
        array.add("Total");

        UsuarioDTO2 encabezado = new UsuarioDTO2();
        Collection<UsuarioDTO2> todo = new ArrayList<>();

        ArrayList<Object> detalleCartera = (ArrayList<Object>) detallePago.get("detalleCartera");
        ArrayList<Object> reportDto1 = new ArrayList<>();
        ArrayList<Object> reportDto2 = new ArrayList<>();
        ArrayList<Object> reportDto3 = new ArrayList<>();
        ArrayList<Object> reportDto4 = new ArrayList<>();
        ArrayList<Object> reportDto5 = new ArrayList<>();
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
                //ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                ArrayList<String> report = new ArrayList<>();
                report.add(objects.get(0).toString());
                report.add(objects.get(1).toString());
                report.add(objects.get(2).toString());
                report.add((objects.get(3).toString()));
                report.add((objects.get(4).toString()));
                report.add(objects.get(5).toString());
                report.add(objects.get(6).toString());
                report.add((objects.get(7).toString()));
                report.add(objects.get(8).toString());
                report.add((objects.get(9).toString()));
                report.add(objects.get(10).toString());
                reportDto1.add(report);
                cont1 += Double.parseDouble(objects.get(7).toString());
                encabezado.setTotal1(cont1);
                //encabezado.setReporteInteresesCarteraFechasDTO1(reportDto1);
            }
            // 0 y 2
            if (!estadoInteres && idEstado == 2 ){
                //ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                ArrayList<String> report = new ArrayList<>();
                report.add((objects.get(0).toString()));
                report.add(objects.get(1).toString());
                report.add(objects.get(2).toString());
                report.add((objects.get(3).toString()));
                report.add((objects.get(4).toString()));
                report.add(objects.get(5).toString());
                report.add(objects.get(6).toString());
                report.add((objects.get(7).toString()));
                report.add(objects.get(8).toString());
                report.add((objects.get(9).toString()));
                report.add(objects.get(10).toString());
                reportDto2.add(report);
                cont2 += Double.parseDouble(objects.get(7).toString());
                if(cont2 == null){
                    encabezado.setTotal2(0.00);
                } else {
                    encabezado.setTotal2(cont2);
                }

                //encabezado.setReporteInteresesCarteraFechasDTO2(reportDto2);
            }
            // 1 y 2
            if (estadoInteres && idEstado == 2 ){
                //ReporteInteresesCarteraFechasDTO report = new ReporteInteresesCarteraFechasDTO();
                ArrayList<String> report = new ArrayList<>();
                report.add((objects.get(0).toString()));
                report.add(objects.get(1).toString());
                report.add(objects.get(2).toString());
                report.add((objects.get(3).toString()));
                report.add((objects.get(4).toString()));
                report.add(objects.get(5).toString());
                report.add(objects.get(6).toString());
                report.add((objects.get(7).toString()));
                report.add(objects.get(8).toString());
                report.add((objects.get(9).toString()));
                report.add(objects.get(10).toString());
                reportDto3.add(report);
                cont3 += Double.parseDouble(objects.get(7).toString());
                if(cont3 == null){
                    encabezado.setTotal3(0.00);
                } else {
                    encabezado.setTotal3(cont3);
                }
                //encabezado.setReporteInteresesCarteraFechasDTO3(reportDto3);
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

                //encabezado.setReporteInteresesCarteraFechasDTO4(reportDto4);
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
                //encabezado.setReporteInteresesCarteraFechasDTO5(reportDto5);
            }
        }

        ByteArrayInputStream in = excelReport.generarExcelInteres(array, reportDto1, reportDto2, reportDto3, reportDto4,
                reportDto5);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=subVariables.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    /*@RequestMapping(value = "interesesCarteraFechas", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
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
    }*/

}
