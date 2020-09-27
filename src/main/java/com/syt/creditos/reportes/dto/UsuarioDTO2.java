package com.syt.creditos.reportes.dto;

import java.util.ArrayList;

// import java.math.BigInteger;


public class UsuarioDTO2 {


    Integer idUsuarios;
    String primerNombres;
    String primerApellidos;
    String direccions;
    String emails;
    Double total1;
    Double total2;
    Double total3;
    Double total4;
    Double total5;
    ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO1;
    ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO2;
    ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO3;
    ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO4;
    ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO5;

    public UsuarioDTO2() {
    }

    public UsuarioDTO2(Integer idUsuarios, String primerNombres, String primerApellidos, String direccions, String emails, Double total1, Double total2, Double total3, Double total4, Double total5, ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO1, ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO2, ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO3, ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO4, ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO5) {
        this.idUsuarios = idUsuarios;
        this.primerNombres = primerNombres;
        this.primerApellidos = primerApellidos;
        this.direccions = direccions;
        this.emails = emails;
        this.total1 = total1;
        this.total2 = total2;
        this.total3 = total3;
        this.total4 = total4;
        this.total5 = total5;
        this.reporteInteresesCarteraFechasDTO1 = reporteInteresesCarteraFechasDTO1;
        this.reporteInteresesCarteraFechasDTO2 = reporteInteresesCarteraFechasDTO2;
        this.reporteInteresesCarteraFechasDTO3 = reporteInteresesCarteraFechasDTO3;
        this.reporteInteresesCarteraFechasDTO4 = reporteInteresesCarteraFechasDTO4;
        this.reporteInteresesCarteraFechasDTO5 = reporteInteresesCarteraFechasDTO5;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getPrimerNombres() {
        return primerNombres;
    }

    public void setPrimerNombres(String primerNombres) {
        this.primerNombres = primerNombres;
    }

    public String getPrimerApellidos() {
        return primerApellidos;
    }

    public void setPrimerApellidos(String primerApellidos) {
        this.primerApellidos = primerApellidos;
    }

    public String getDireccions() {
        return direccions;
    }

    public void setDireccions(String direccions) {
        this.direccions = direccions;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Double getTotal1() {
        return total1;
    }

    public void setTotal1(Double total1) {
        this.total1 = total1;
    }

    public Double getTotal2() {
        return total2;
    }

    public void setTotal2(Double total2) {
        this.total2 = total2;
    }

    public Double getTotal3() {
        return total3;
    }

    public void setTotal3(Double total3) {
        this.total3 = total3;
    }

    public Double getTotal4() {
        return total4;
    }

    public void setTotal4(Double total4) {
        this.total4 = total4;
    }

    public Double getTotal5() {
        return total5;
    }

    public void setTotal5(Double total5) {
        this.total5 = total5;
    }

    public ArrayList<ReporteInteresesCarteraFechasDTO> getReporteInteresesCarteraFechasDTO1() {
        return reporteInteresesCarteraFechasDTO1;
    }

    public void setReporteInteresesCarteraFechasDTO1(ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO1) {
        this.reporteInteresesCarteraFechasDTO1 = reporteInteresesCarteraFechasDTO1;
    }

    public ArrayList<ReporteInteresesCarteraFechasDTO> getReporteInteresesCarteraFechasDTO2() {
        return reporteInteresesCarteraFechasDTO2;
    }

    public void setReporteInteresesCarteraFechasDTO2(ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO2) {
        this.reporteInteresesCarteraFechasDTO2 = reporteInteresesCarteraFechasDTO2;
    }

    public ArrayList<ReporteInteresesCarteraFechasDTO> getReporteInteresesCarteraFechasDTO3() {
        return reporteInteresesCarteraFechasDTO3;
    }

    public void setReporteInteresesCarteraFechasDTO3(ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO3) {
        this.reporteInteresesCarteraFechasDTO3 = reporteInteresesCarteraFechasDTO3;
    }

    public ArrayList<ReporteInteresesCarteraFechasDTO> getReporteInteresesCarteraFechasDTO4() {
        return reporteInteresesCarteraFechasDTO4;
    }

    public void setReporteInteresesCarteraFechasDTO4(ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO4) {
        this.reporteInteresesCarteraFechasDTO4 = reporteInteresesCarteraFechasDTO4;
    }

    public ArrayList<ReporteInteresesCarteraFechasDTO> getReporteInteresesCarteraFechasDTO5() {
        return reporteInteresesCarteraFechasDTO5;
    }

    public void setReporteInteresesCarteraFechasDTO5(ArrayList<ReporteInteresesCarteraFechasDTO> reporteInteresesCarteraFechasDTO5) {
        this.reporteInteresesCarteraFechasDTO5 = reporteInteresesCarteraFechasDTO5;
    }
}

