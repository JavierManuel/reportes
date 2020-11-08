package com.syt.creditos.reportes.service;

import com.syt.creditos.reportes.dto.ReporteMorasDosGraciaDTO;
import com.syt.creditos.reportes.dto.ReporteMorasUnoDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelReport {

    public static ByteArrayInputStream generarExcel(ArrayList<String> titulos, ArrayList<Object> datos) throws IOException {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Datos");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // Header Row
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < titulos.size(); i ++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(titulos.get(i).toString());
            }

            for (int i = 0; i < datos.size(); i++) {
                ArrayList list = (ArrayList) datos.get(i);
                Row headerRow2 = sheet.createRow(i + 1);
                for (int j = 0; j < titulos.size(); j++) {
                    Cell cell = headerRow2.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream generarExcel2(ArrayList<String> titulos, ArrayList<Object> datos, ArrayList<String> titulos2, ArrayList<Object> datos2) throws IOException {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Datos");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // Header Row
            Row headerRow = sheet.createRow(0);
            Cell headerCellSub1 = headerRow.createCell(0);
            headerCellSub1.setCellValue("EN MORA");

            Row headerRow1 = sheet.createRow(1);

            for (int i = 0; i < titulos.size(); i ++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(titulos.get(i).toString());
            }

            for (int i = 0; i < datos.size(); i++) {
                ArrayList list = (ArrayList) datos.get(i);
                Row headerRow2 = sheet.createRow(i + 1);
                for (int j = 0; j < titulos.size(); j++) {
                    Cell cell = headerRow2.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }


            Row headerRowSub2 = sheet.createRow(!datos.isEmpty() ? (datos.size() + 4) : 4);
            Cell headerCellSub2 = headerRowSub2.createCell(0);
            headerCellSub2.setCellValue("EN DIAS DE GRACIA");

            // Header Row
            Row headerRowInitial2 = sheet.createRow(!datos.isEmpty() ? (datos.size() + 5) : 5);

            for (int i = 0; i < titulos2.size(); i ++) {
                Cell headerCell = headerRowInitial2.createCell(i);
                headerCell.setCellValue(titulos2.get(i).toString());
            }


            for (int i = 0; i < datos2.size(); i++) {
                ArrayList list = (ArrayList) datos2.get(i);
                Row headerRow2 = sheet.createRow(i + (!datos2.isEmpty() ? (datos2.size() + 6) : 6));
                for (int j = 0; j < titulos2.size(); j++) {
                    Cell cell = headerRow2.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }



            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }






}
