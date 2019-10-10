package com.syt.creditos.reportes.configuration;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;

public class Generator {

    public void generatorReport(HashMap map){
        String path = new String();
        try {
            JasperPrint informe = JasperFillManager.fillReport(path, map, new JREmptyDataSource());
            JasperViewer.viewReport(informe, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
