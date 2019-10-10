package com.syt.creditos.reportes.interfaces;

import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * Generates a HTML report with the given input file. Uses a JREmptyDataSource
     *
     * @param inputFileName
     *            report source file without extension
     * @param params
     *            report parameters
     * @return the String containing the HTML
     */
    String generateHtmlReport(String inputFileName, Map<String, Object> params);

    /**
     * Generates a HTML report with the given input file
     *
     * @param inputFileName
     *            report source file without extension
     * @param params
     *            report parameters
     * @param dataSource
     *            the source of data
     * @return the String containing the HTML
     */
    String generateHtmlReport(String inputFileName, Map<String, Object> params, JRDataSource dataSource);

    /**
     * Generates a HTML report with the given input file. Uses a JREmptyDataSource
     *
     * @param inputFileName
     *            report source file without extension
     * @param params
     *            report parameters
     * @return the List containing the HTML and inline source
     */
    List<Object> generateInlineHtmlReport(String inputFileName, Map<String, Object> params);

    /**
     * Generates a HTML report with the given input file
     *
     * @param inputFileName
     *            report source file without extension
     * @param params
     *            report parameters
     * @param dataSource
     *            the source of data
     * @return the List containing the HTML and a Map populated with inline source(s)
     */
    List<Object> generateInlineHtmlReport(String inputFileName, Map<String, Object> params, JRDataSource jRDataSource);

}
