package com.syt.creditos.reportes.service;

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

    public static ByteArrayInputStream generarExcel(/*List<Object> datos*/) throws IOException {
        int columns = 2;//datos.size();//para generar total de columnas por ID encontrados
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

            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("IdPrestamo");
            //headerCell.setCellStyle(headerCellStyle);

            headerCell = headerRow.createCell(1);
            headerCell.setCellValue("Primer Nombre");
            //headerCell.setCellStyle(headerCellStyle);
            ArrayList<Object> array = new ArrayList<>();
array.add(0, "Luis");
            array.add(1, "Pedro");
            //columns = array.size();
/*
            int col = 0;
            for (Object dato : datos) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue("Name");
                cell.setCellStyle(headerCellStyle);
                col+=2;
            }*/

            Row headerRow2 = sheet.createRow(1);
/*
            Cell cell = headerRow2.createCell(0);
            cell.setCellValue("John Smith");
            //cell.setCellStyle(style);

            cell = headerRow2.createCell(1);
            cell.setCellValue(20);
            //cell.setCellStyle(style);
            */
            for (int colx = 0; colx < columns*2; colx++) {
                Cell cell = headerRow2.createCell(colx);
                if(colx%2==0)
                    cell.setCellValue(array.get(colx).toString());
                else
                    cell.setCellValue(array.get(colx).toString());

                sheet.autoSizeColumn(colx);
            }
/*
            int firstCol = 0;
            int lastCol = 1;

            for (int colx = 0; colx < columns; colx++) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, firstCol, lastCol));
                firstCol+=2;
                lastCol+=2;
                //asi voy fucionando las columnas de 2 en 2 según numero de registro
            }*/
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}
