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


    public static ByteArrayInputStream generarExcel2(ArrayList<String> titulos, ArrayList<Object> datos) throws IOException {

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

    public static ByteArrayInputStream generarExcelInteres(ArrayList<String> titulos, ArrayList<Object> datos1, ArrayList<Object> datos2,
                                                           ArrayList<Object> datos3, ArrayList<Object> datos4, ArrayList<Object> datos5) throws IOException {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Datos");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //header 1
            Row headerRowSub1 = sheet.createRow(0);
            Cell headerCellSub1 = headerRowSub1.createCell(0);
            headerCellSub1.setCellValue("INTERESES PAGADOS");

            // Header Row
            Row headerRowInitial = sheet.createRow(1);

            for (int i = 0; i < titulos.size(); i ++) {
                Cell headerCell = headerRowInitial.createCell(i);
                headerCell.setCellValue(titulos.get(i).toString());
            }


            for (int i = 0; i < datos1.size(); i++) {
                ArrayList list = (ArrayList) datos1.get(i);
                Row headerRow2 = sheet.createRow(i + 2);
                for (int j = 0; j < titulos.size(); j++) {
                    Cell cell = headerRow2.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }

            //header 2
            Row headerRowSub2 = sheet.createRow(!datos1.isEmpty() ? (datos1.size() + 4) : 4);
            Cell headerCellSub2 = headerRowSub2.createCell(0);
            headerCellSub2.setCellValue("INTERESES PERDONADOS EJECUTADOS");

            // Header Row
            Row headerRowInitial2 = sheet.createRow(!datos1.isEmpty() ? (datos1.size() + 5) : 5);

            for (int i = 0; i < titulos.size(); i ++) {
                Cell headerCell = headerRowInitial2.createCell(i);
                headerCell.setCellValue(titulos.get(i).toString());
            }


            for (int i = 0; i < datos2.size(); i++) {
                ArrayList list = (ArrayList) datos2.get(i);
                Row headerRow2 = sheet.createRow(i + (!datos1.isEmpty() ? (datos1.size() + 6) : 6));
                for (int j = 0; j < titulos.size(); j++) {
                    Cell cell = headerRow2.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }

            //header 3
            Row headerRowSub3 = sheet.createRow(!datos2.isEmpty() ? (datos2.size() + 10) : 10);
            Cell headerCellSub3 = headerRowSub3.createCell(0);
            headerCellSub3.setCellValue("INTERESES PERDONADOS PENDIENTES");

            // Header Row
            Row headerRowInitial3 = sheet.createRow(!datos2.isEmpty() ? (datos2.size() + 11) : 11);

            for (int i = 0; i < titulos.size(); i ++) {
                Cell headerCell = headerRowInitial3.createCell(i);
                headerCell.setCellValue(titulos.get(i).toString());
            }


            for (int i = 0; i < datos3.size(); i++) {
                ArrayList list = (ArrayList) datos3.get(i);
                Row headerRow3 = sheet.createRow(i + (!datos2.isEmpty() ? (datos2.size() + 12) : 12));
                for (int j = 0; j < titulos.size(); j++) {
                    Cell cell = headerRow3.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }

            //header 4
            Row headerRowSub4 = sheet.createRow(!datos3.isEmpty() ? (datos3.size() + 15) : 15);
            Cell headerCellSub4 = headerRowSub4.createCell(0);
            headerCellSub4.setCellValue("INTERESES PENDIENTES PAGADOS");

            // Header Row
            Row headerRowInitial4 = sheet.createRow(!datos3.isEmpty() ? (datos3.size() + 16) : 16);

            for (int i = 0; i < titulos.size(); i ++) {
                Cell headerCell = headerRowInitial4.createCell(i);
                headerCell.setCellValue(titulos.get(i).toString());
            }


            for (int i = 0; i < datos4.size(); i++) {
                ArrayList list = (ArrayList) datos4.get(i);
                Row headerRow4 = sheet.createRow(i + (!datos3.isEmpty() ? (datos3.size() + 17) : 17));
                for (int j = 0; j < titulos.size(); j++) {
                    Cell cell = headerRow4.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }

            //header 5
            Row headerRowSub5 = sheet.createRow(!datos4.isEmpty() ? (datos4.size() + 20) : 20);
            Cell headerCellSub5 = headerRowSub5.createCell(0);
            headerCellSub5.setCellValue("INTERESES PENDIENTES");

            // Header Row
            Row headerRowInitial5 = sheet.createRow(!datos4.isEmpty() ? (datos4.size() + 21) : 21);

            for (int i = 0; i < titulos.size(); i ++) {
                Cell headerCell = headerRowInitial5.createCell(i);
                headerCell.setCellValue(titulos.get(i).toString());
            }


            for (int i = 0; i < datos5.size(); i++) {
                ArrayList list = (ArrayList) datos5.get(i);
                Row headerRow5 = sheet.createRow(i + (!datos4.isEmpty() ? (datos4.size() + 22) : 22));
                for (int j = 0; j < titulos.size(); j++) {
                    Cell cell = headerRow5.createCell(j);
                    cell.setCellValue(list.get(j) == null ? "" : list.get(j).toString());
                    sheet.autoSizeColumn(j);
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }



}
