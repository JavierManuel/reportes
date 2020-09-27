package com.syt.creditos.reportes.dto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;

// import java.math.BigInteger;


public class ReporteCreditosCarteraDTO {


    Long idPrestamo;
    String primerNombre;
    String primerApellido;
    Double montoAsignado;
    Integer plazoMeses;
    String nombrePlan;
    String garantia;

    public ReporteCreditosCarteraDTO(Long idPrestamo, String primerNombre, String primerApellido, Double montoAsignado, Integer plazoMeses, String nombrePlan, String garantia) {
        this.idPrestamo = idPrestamo;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.montoAsignado = montoAsignado;
        this.plazoMeses = plazoMeses;
        this.nombrePlan = nombrePlan;
        this.garantia = garantia;
    }

    public ReporteCreditosCarteraDTO() {
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public Double getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(Double montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }


}
