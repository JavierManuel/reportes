package com.syt.creditos.reportes.controller;

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

}
