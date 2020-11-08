package com.syt.creditos.reportes.controller;

import com.syt.creditos.reportes.dto.ReporteMorasDTO;
import com.syt.creditos.reportes.dto.ReporteMorasDosGraciaDTO;
import com.syt.creditos.reportes.dto.ReporteMorasUnoDTO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public ResponseEntity<InputStreamResource> reporteMorasDias() throws IOException, ParseException {


        Map<String, Object> detallePago = pagoFeing.reporteMorasDias();
        System.out.println(detallePago.get("detalle"));

//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("pago", Double.parseDouble(params.get("montoPagado").toString()));

        ReporteMorasDTO encabezado = new ReporteMorasDTO();
        Collection<UsuarioDTO2> todo = new ArrayList<>();
        ArrayList<Object> detalle = (ArrayList<Object>) detallePago.get("detalle");
        ArrayList<Object> reportDto1 = new ArrayList<>();
        ArrayList<Object> reportDto2 = new ArrayList<>();

        if (detallePago != null) {



            Double cont1 = 0.00, cont2 = 0.00;
            Date date = new Date();

            for(Integer i = 0; i < detalle.size(); i++){
                ArrayList objects = (ArrayList) detalle.get(i);

                Date fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(objects.get(5).toString());
                Long dias = ((date.getTime() - fechaIni.getTime())/86400000);
                System.out.println(dias);

                // en mora ya contando
                if (dias > 3){
                    ArrayList<String> report = new ArrayList<>();
                    report.add(objects.get(0).toString());
                    report.add(objects.get(1).toString());
                    report.add(objects.get(2).toString());
                    report.add(objects.get(3).toString());
                    report.add(objects.get(4).toString());
                    report.add(dias.toString());
                    report.add(String.valueOf((dias*Double.parseDouble(objects.get(4).toString()))));
                    reportDto1.add(report);
//                    cont1 += report.getTotalMora();
//                    if(cont1 == null){
//                        encabezado.setTotal1(0.00);
//                    } else {
//                        encabezado.setTotal1(cont1);
//                    }
//                    encabezado.setReporteMorasUnoDTO(reportDto1);
                } else {

//                    ReporteMorasDosGraciaDTO report = new ReporteMorasDosGraciaDTO();
                    ArrayList<String> report = new ArrayList<>();
                    report.add(objects.get(0).toString());
                    report.add(objects.get(1).toString());
                    report.add(objects.get(2).toString());
                    report.add(objects.get(3).toString());
                    report.add(objects.get(4).toString());
                    report.add(dias.toString());
                    reportDto2.add(report);
//                    encabezado.setReporteMorasDosGraciaDTO(reportDto2);

                }


            }
        }

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

//        ByteArrayInputStream in = excelReport.generarExcel(array, encabezado.getReporteMorasUnoDTO());
        ByteArrayInputStream in = excelReport.generarExcel2(array, reportDto1, array2, reportDto2);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=subVariables.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
