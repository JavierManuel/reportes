package com.syt.creditos.reportes.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// import java.math.BigInteger;


public class UsuarioDTO{


    Integer idUsuarios;
    String primerNombres;
    String primerApellidos;
    String direccions;
    String emails;
    ArrayList<ReporteCreditosCarteraDTO> reporteCreditosCarteraDTO;

    public UsuarioDTO(Integer idUsuarios, String primerNombres, String primerApellidos, String direccions, String emails, ArrayList<ReporteCreditosCarteraDTO> reporteCreditosCarteraDTO) {
        this.idUsuarios = idUsuarios;
        this.primerNombres = primerNombres;
        this.primerApellidos = primerApellidos;
        this.direccions = direccions;
        this.emails = emails;
        this.reporteCreditosCarteraDTO = reporteCreditosCarteraDTO;
    }

    public UsuarioDTO(){

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

    public ArrayList<ReporteCreditosCarteraDTO> getReporteCreditosCarteraDTO() {
        return reporteCreditosCarteraDTO;
    }

    public void setReporteCreditosCarteraDTO(ArrayList<ReporteCreditosCarteraDTO> reporteCreditosCarteraDTO) {
        this.reporteCreditosCarteraDTO = reporteCreditosCarteraDTO;
    }


}

