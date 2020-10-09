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
        array.add("PrimerNombre");
        array.add("PrimerApellido");
        array.add("Abono Capital");
        array.add("Interés");
        array.add("Cuota Seguro");
        array.add("Total");
        array.add("PagoExtraCapital");
        array.add("FechaSistema");

        ByteArrayInputStream in = excelReport.generarExcel(array, detallePago);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=subVariables.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
