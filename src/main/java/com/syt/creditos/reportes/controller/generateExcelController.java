package com.syt.creditos.reportes.controller;

import com.syt.creditos.reportes.service.ExcelReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("excel")
public class generateExcelController {

    @Autowired
    private ExcelReport excelReport;

    @PostMapping(value = "/excelexport")
    public ResponseEntity<InputStreamResource> subVaribleExcel(/*@RequestBody SubVariable variable*/) throws IOException {
        //List<SubVariableExcel> subVariableExcels = subVariableRepository.findAllForExcel(variable.getVariable_id());
        ByteArrayInputStream in = excelReport.generarExcel();//SubVariableExcelReport.subVariableExcel(subVariableExcels);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=subVariables.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
